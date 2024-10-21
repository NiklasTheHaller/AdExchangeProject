<template>
    <div class="max-w-md mx-auto">
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <!-- <UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">-->

        <UForm :schema="schema" :state="state" class="space-y-4">

            <UFormGroup label="Email" name="email" size="xl">
                <UInput type="email" placeholder="you@example.com" icon="i-heroicons-envelope" />
                <!-- v-model="state.email"-->
            </UFormGroup>

            <UFormGroup label="Password" name="password" size="xl">
                <template #hint>
                    <NuxtLink to="/reset-password" class="text-primary-500 underline">Forgot?</NuxtLink>
                </template>

                <!-- v-model="state.password"-->
                <UInput type="password" placeholder="Enter Password" icon="i-heroicons-lock-closed" />
            </UFormGroup>

            <UButton ui: type="submit" size="xl" class="login-button">
                Sign In
            </UButton>

            <div class="inline-elements">
                <p>Don't have an account?</p>
                <ULink to="/registrationPage" class="text-primary-500 underline">Sign Up</ULink>
            </div>

        </UForm>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>
        <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</H1>

    </div>
</template>

<script lang="ts">
import type { _padding } from '#tailwind-config/theme';


</script>

<style scoped>
.inline-elements {
    display: flex;
    align-items: center;
}

.inline-elements> :first-child {
    margin-right: 6px;
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