<template>
	<div>
		<h1 class="text-2xl font-bold mb-6">Registration</h1>
		<!-- remove novalidate after testing -->
		<form class="max-w-md mx-auto" novalidate @submit.prevent="handleSubmit">
			<UFormGroup label="Username" name="username" class="mb-4">
				<UInput
					v-model.trim="form.username"
					placeholder="Choose a unique username" />
			</UFormGroup>

			<UFormGroup label="Email" name="email" class="mb-4">
				<UInput
					v-model.trim="form.email"
					type="email"
					placeholder="Enter your email" />
				<p v-if="form.email && !isEmailValid" class="text-red-500 text-sm mt-1">
					Please enter a valid email address.
				</p>
			</UFormGroup>

			<UFormGroup label="Confirm Email" name="confirmEmail" class="mb-4">
				<UInput
					v-model.trim="form.confirmEmail"
					type="email"
					placeholder="Confirm your email" />
				<p
					v-if="form.confirmEmail && !emailsMatch"
					class="text-red-500 text-sm mt-1">
					Emails do not match.
				</p>
			</UFormGroup>

			<UFormGroup label="Password" name="password" class="mb-4">
				<UInput
					v-model="form.password"
					type="password"
					placeholder="Choose a strong password"
					:class="{ 'border-red-500': !isPasswordValid && form.password }" />
				<p class="text-sm text-gray-600 mt-1">
					Password must be at least 8 characters long, contain one uppercase
					letter, and one number or special character.
				</p>
				<p
					v-if="form.password && !isPasswordValid"
					class="text-red-500 text-sm mt-1">
					Password does not meet the requirements.
				</p>
			</UFormGroup>

			<UFormGroup label="Confirm Password" name="confirmPassword" class="mb-6">
				<UInput
					v-model="form.confirmPassword"
					type="password"
					placeholder="Confirm your password" />
				<p
					v-if="form.confirmPassword && !passwordsMatch"
					class="text-red-500 text-sm mt-1">
					Passwords do not match.
				</p>
			</UFormGroup>

			<UButton
				type="submit"
				color="primary"
				class="w-full"
				:loading="canSubmit && isLoading"
				:disabled="!canSubmit || isLoading">
				{{ isLoading ? 'Registering...' : 'Register' }}
			</UButton>
		</form>
	</div>
</template>

<script setup>
import { useNuxtApp } from '#app';

const nuxtApp = useNuxtApp();
const isLoading = ref(false);

const form = ref({
	username: '',
	email: '',
	confirmEmail: '',
	password: '',
	confirmPassword: '',
});

const { error, execute } = $fetch(
	'https://jsonplaceholder.typicode.com/users',
	{
		method: 'POST',
		lazy: true,
	}
);

// Computed properties remain the same
const emailsMatch = computed(
	() => form.value.email === form.value.confirmEmail
);
const passwordsMatch = computed(
	() => form.value.password === form.value.confirmPassword
);

const passwordRegex = /^(?=.*[A-Z])(?=.*[\d\W]).{8,}$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const isPasswordValid = computed(() => passwordRegex.test(form.value.password));
const isEmailValid = computed(() => emailRegex.test(form.value.email));

const canSubmit = computed(
	() =>
		Object.values(form.value).every((field) => field) &&
		emailsMatch.value &&
		passwordsMatch.value &&
		isPasswordValid.value &&
		isEmailValid.value
);

// Form submission handler
const handleSubmit = async () => {
	if (!canSubmit.value) return;

	try {
		isLoading.value = true;
		await execute({ body: form.value });
		if (error.value) throw new Error('Registration failed');

		showNotification(
			'Success',
			`Registration successful! Welcome, ${form.value.username}`,
			'success'
		);
		resetForm();
	} catch (e) {
		showNotification('Error', e.message || 'Something went wrong', 'error');
	} finally {
		console.log(
			form.value.username,
			form.value.email,
			form.value.confirmEmail,
			form.value.password,
			form.value.confirmPassword
		);
		isLoading.value = false;
	}
};

// Utility function for notifications
const showNotification = (title, message, type) => {
	nuxtApp.$toast[type](message, { title });
};

const resetForm = () => {
	form.value = {
		username: '',
		email: '',
		confirmEmail: '',
		password: '',
		confirmPassword: '',
	};
};
</script>
