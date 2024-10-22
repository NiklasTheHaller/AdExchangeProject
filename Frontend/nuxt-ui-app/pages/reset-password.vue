<template>
    <div class='centerDiv'>
        <div class="max-w-md mx-auto">
            <h1 class="text-2xl font-bold mb-6">Forgot Password?</H1>

            <p>
                Enter the email address you used when you joined and we’ll send you instructions to reset your password.
            </p>
            <UForm :schema="schema" :state="state" class="space-y-4">

                <UFormGroup label="Email Address" name="email" class="mt-5">

                    <UInput type="email" placeholder="you@example.com" icon="i-heroicons-envelope" />
                    <!-- v-model="state.email"-->
                </UFormGroup>

                <UButton ui: type="submit" size="xl" class="login-button">
                    Send Reset Instructions
                </UButton>

            </UForm>
        </div>
    </div>
</template>

<script lang="ts">
// TODO: Do a script that sent an email to reboot password to the right email adress

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