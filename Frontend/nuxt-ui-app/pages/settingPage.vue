<script setup lang="ts">
import { object, string, ref, type InferType } from 'yup';
import { reactive, computed } from 'vue';
//import creditCardInfo from '~/components/credit-card-info.vue';

const colorMode = useColorMode();

const isDark = computed({
  get() {
    return colorMode.value === 'dark';
  },
  set() {
    colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark';
  }
});

const items = [
  { slot: 'Account', label: 'Account', icon: 'i-heroicons-user-circle' },
  { slot: 'Billing', label: 'Billing', icon: 'i-heroicons-credit-card' }
];

const schema = object({
    email: string().email('Invalid email').required('Required'),
    username: string().required('Required'),
    password: string().min(8, 'Must be at least 8 characters').required('Required'),
    newPassword: string().min(8, 'Must be at least 8 characters').required('Required'),
    confirmPassword: string().oneOf([ref('newPassword')], 'Passwords must match').required('Required')
});

type Schema = InferType<typeof schema>;

const state = reactive<Schema>({
    email: '',
    username: '',
    password: '',
    newPassword: '',
    confirmPassword: ''
});

async function onSubmit(event: Event) {
    event.preventDefault();
    try {
        await schema.validate(state);
        console.log('Form submitted:', state);
        // TODO: Call API to launch a settings change data procedure
    } catch (error) {
        console.error('Validation error:', error);
    }
}

// Schéma de validation pour le formulaire Billing
const billingSchema = object({
  cardholderName: string().required('Cardholder name is required'),
  cardNumber: string().matches(/^[0-9]{16}$/, 'Invalid card number').required('Card number is required'),
  expireMonth: string().matches(/^(0[1-9]|1[0-2])$/, 'Invalid month').required('Expiration month is required'),
  expireYear: string().matches(/^\d{4}$/, 'Invalid year').required('Expiration year is required'),
  cvv: string().matches(/^\d{3,4}$/, 'Invalid CVV').required('CVV is required')
});
type BillingSchema = InferType<typeof billingSchema>;

const billingState = reactive<BillingSchema>({
  cardholderName: '',
  cardNumber: '',
  expireMonth: '',
  expireYear: '',
  cvv: ''
});

async function saveBillingInfo() {
  try {
    await billingSchema.validate(billingState);
    console.log('Billing information saved:', billingState);
  } catch (error) {
    console.error('Validation error:', error);
  }
}
</script>


<template>
    <UTabs :items="items" class="w-full">
        <template #icon="{ item, selected }">
            <UIcon 
            :name="item.icon" class="w-4 h-4 flex-shrink-0 me-2"
                :class="[selected && 'text-primary-500 dark:text-primary-400']" />
        </template>

        <template #Account>
            <UCard @submit="onSubmit">
                <template #header>
                    <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
                        Account
                    </p>
                    <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
                        Make changes to your account here. Click save when you're done.
                    </p>
                </template>

                <div class="flex justify-center w-[90%] mx-auto space-x-4">
                    <div class="flex-1 space-y-4 ">
                        <UFormGroup label="Email" name="email" size="xl">
                            <UInput 
                            v-model="state.email" type="email" placeholder="you@example.com"
                                icon="i-heroicons-envelope" />
                        </UFormGroup>

                        <UFormGroup label="Username" name="username" size="xl">
                            <UInput 
                            v-model="state.username" type="text" placeholder="Display Username"
                                icon="i-heroicons-user" />
                        </UFormGroup>
                    </div>

                    <UDivider orientation="vertical" />

                    <div class="flex-1 space-y-4">
                        <UFormGroup label="Current Password" name="password" size="xl">
                            <UInput 
                            v-model="state.password" type="password" placeholder="Enter Current Password"
                                icon="i-heroicons-lock-closed" />
                        </UFormGroup>

                        <UFormGroup label="New Password" name="newPassword" size="xl">
                            <UInput 
                            v-model="state.newPassword" type="password" placeholder="Enter New Password"
                                icon="i-heroicons-lock-closed" />
                        </UFormGroup>

                        <UFormGroup label="Confirm Password" name="confirmPassword" size="xl">
                            <UInput 
                            v-model="state.confirmPassword" type="password" placeholder="Confirm New Password"
                                icon="i-heroicons-lock-closed" />
                        </UFormGroup>
                    </div>
                </div>

                <template #footer>
                    <div class="flex justify-end space-x-2">
                        <UButton type="button" color="gray">
                            Cancel
                        </UButton>
                        <UButton type="submit" :class="isDark ? 'bg-primary' : 'bg-black text-white'">
                            Save account
                        </UButton>
                    </div>
                </template>
            </UCard>
        </template>
  
      <!-- Onglet Billing -->
      <template #Billing>
        <UCard>
          <template #header>
            <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">Billing</p>
            <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">Update your billing information here.</p>
          </template>
          
          <div class="space-y-6">
            <!-- TODO: Display component when is operationnal
            <creditCardInfo
              :cardholder="billingState.cardholderName"
              :cardnumber="billingState.cardNumber"
              :expire_month="billingState.expireMonth"
              :expire_year="billingState.expireYear"
              :cvv="billingState.cvv"
            />
            -->
  
            <!-- Formulaire de facturation -->
            <form class="space-y-4" @submit.prevent="saveBillingInfo">
              <UFormGroup label="Cardholder Name" name="cardholderName">
                <UInput v-model="billingState.cardholderName" placeholder="John Doe" />
              </UFormGroup>
              <UFormGroup label="Card Number" name="cardNumber">
                <UInput v-model="billingState.cardNumber" placeholder="1234 5678 9012 3456" />
              </UFormGroup>
              <div class="flex space-x-4">
                <UFormGroup label="Expiration Month" name="expireMonth" class="w-1/2">
                  <UInput v-model="billingState.expireMonth" placeholder="MM" />
                </UFormGroup>
                <UFormGroup label="Expiration Year" name="expireYear" class="w-1/2">
                  <UInput v-model="billingState.expireYear" placeholder="YYYY" />
                </UFormGroup>
              </div>
              <UFormGroup label="CVV" name="cvv">
                <UInput v-model="billingState.cvv" type="password" placeholder="123" />
              </UFormGroup>
            </form>
          </div>
  
          <template #footer>
            <div class="flex justify-end">
              <UButton type="button" :class="isDark ? 'bg-primary' : 'bg-black text-white'" @click="saveBillingInfo">
                Save billing info
              </UButton>
            </div>
          </template>
        </UCard>
      </template>
    </UTabs>
  </template>
  