<template>
    <div class='centerDiv'>
        <div class="max-w-md mx-auto">
            <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>

            <UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">

                <UFormGroup label="Email" name="email" size="xl">
                    <UInput 
                    v-model="state.email" type="email" placeholder="you@example.com"
                        icon="i-heroicons-envelope" />
                </UFormGroup>

                <UFormGroup label="Password" name="password" size="xl">
                    <template #hint>
                        <NuxtLink to="/reset-password" class="text-primary-500 underline">Forgot?</NuxtLink>
                    </template>

                    <UInput 
                    v-model="state.password" type="password" placeholder="Enter Password"
                        icon="i-heroicons-lock-closed" />
                </UFormGroup>

                <UButton ui: type="submit" size="xl" class="login-button">
                    Sign In
                </UButton>

                <div class="inline-elements">
                    <p>Don't have an account?</p>
                    <ULink to="/registrationPage" class="text-primary-500 underline">Sign Up</ULink>
                </div>

            </UForm>
        </div>
    </div>
</template>

<script setup lang="ts">
import { object, string, type InferType } from 'yup'
import type { FormSubmitEvent } from '#ui/types'

const schema = object({
    email: string().email('Invalid email').required('Required'),
    password: string()
        .min(8, 'Must be at least 8 characters')
        .required('Required')
})

type Schema = InferType<typeof schema>

const state = reactive({
    email: undefined,
    password: undefined
})

async function onSubmit(event: FormSubmitEvent<Schema>) {
    // TODO: Call API to launch a login procedure
    console.log(event.data)
}

</script>

<style scoped>
.inline-elements {
    display: flex;
    align-items: center;
}

.inline-elements> :first-child {
    margin-right: 6px;
}

.centerDiv {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
    width: 90%;
}

.login-button {
    width: 100%;
    font-weight: bold;
    font-size: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px !important;
}
</style>