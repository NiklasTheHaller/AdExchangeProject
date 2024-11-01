<script setup lang="ts">
import { object, string, ref, type InferType } from 'yup';
import { reactive } from 'vue';

const items = [
  { slot: 'Account', label: 'Account', icon: 'i-heroicons-user-circle' },
  { slot: 'Appearance', label: 'Appearance', icon: 'i-heroicons-paint-brush' },
  { slot: 'Notifications', label: 'Notifications', icon: 'i-heroicons-bell' },
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
</script>

<template>
  <UTabs :items="items" class="w-full">
    <template #icon="{ item, selected }">
      <UIcon :name="item.icon" class="w-4 h-4 flex-shrink-0 me-2" :class="[selected && 'text-primary-500 dark:text-primary-400']" />
    </template>
    
    <template #Account>
  <UCard :ui="{ body: { base: 'grid grid-cols-3' } }" @submit="onSubmit">
    <template #header>
      <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
        Account
      </p>
      <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
        Make changes to your account here. Click save when you're done.
      </p>
    </template>
                             
    <div class="space-y-4 flex flex-col justify-center">
        <UFormGroup label="Email" name="email" size="xl">
          <UInput v-model="state.email" type="email" placeholder="you@example.com" icon="i-heroicons-envelope" />
        </UFormGroup>

        <UFormGroup label="Username" name="username" size="xl">
          <UInput v-model="state.username" type="text" placeholder="Display Username" icon="i-heroicons-user" />
        </UFormGroup>
      </div>

      <UDivider orientation="vertical" />

      <div class="space-y-4 flex flex-col justify-center">
                                <UFormGroup label="Current Password" name="password" size="xl">
          <UInput v-model="state.password" type="password" placeholder="Enter Current Password" icon="i-heroicons-lock-closed" />
        </UFormGroup>

        <UFormGroup label="New Password" name="newPassword" size="xl">
          <UInput v-model="state.newPassword" type="password" placeholder="Enter New Password" icon="i-heroicons-lock-closed" />
        </UFormGroup>
        
        <UFormGroup label="Confirm Password" name="confirmPassword" size="xl">
          <UInput v-model="state.confirmPassword" type="password" placeholder="Confirm New Password" icon="i-heroicons-lock-closed" />
        </UFormGroup>
      </div>
      
    <template #footer>
      <div class="flex justify-end space-x-2">
        <UButton type="button" color="gray">
          Cancel
        </UButton>
        <UButton type="submit" color="black">
          Save account
        </UButton>
      </div>
    </template>
  </UCard>
</template>


    <template #Appearance>
      <UCard>
        <template #header>
          <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
            Appearance
          </p>
          <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
            Customize the appearance settings here.
          </p>
        </template>

        <!-- Add appearance-related form fields here -->

        <template #footer>
          <UButton type="button" color="black">
            Save appearance
          </UButton>
        </template>
      </UCard>
    </template>

    <template #Notifications>
      <UCard>
        <template #header>
          <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
            Notifications
          </p>
          <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
            Manage your notification preferences here.
          </p>
        </template>

        <!-- Add notification-related form fields here -->

        <template #footer>
          <UButton type="button" color="black">
            Save notifications
          </UButton>
        </template>
      </UCard>
    </template>

    <template #Billing>
      <UCard>
        <template #header>
          <p class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
            Billing
          </p>
          <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
            Update your billing information here.
          </p>
        </template>

        <!-- Add billing-related form fields here -->

        <template #footer>
          <UButton type="button" color="black">
            Save billing info
          </UButton>
        </template>
      </UCard>
    </template>

  </UTabs>
</template>
