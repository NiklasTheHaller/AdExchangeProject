<template>
	<div>
		<div class="centerDiv max-w-md mx-auto">
			<h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</h1>

			<UForm
				:schema="schema"
				:state="state"
				class="space-y-4"
				@submit="onSubmit">
				<UFormGroup label="Email" name="email" size="xl">
					<UInput
						v-model="state.email"
						type="email"
						placeholder="you@example.com"
						icon="i-heroicons-envelope" />
				</UFormGroup>

				<UFormGroup label="Password" name="password" size="xl">
					<template #hint>
						<NuxtLink to="/reset-password" class="text-primary-500 underline"
							>Forgot?</NuxtLink
						>
					</template>

					<UInput
						v-model="state.password"
						type="password"
						placeholder="Enter Password"
						icon="i-heroicons-lock-closed" />
				</UFormGroup>

				<UFormGroup name="rememberMe" class="flex items-center">
					<UCheckbox v-model="state.rememberMe" label="Remember me" />
				</UFormGroup>

				<UButton
					type="submit"
					size="xl"
					class="login-button"
					:loading="isLoading"
					:disabled="isLoading">
					{{ isLoading ? 'Signing In...' : 'Sign In' }}
				</UButton>

				<UAlert
					v-if="errorMessage"
					color="red"
					icon="i-heroicons-exclamation-circle"
					class="mt-4">
					{{ errorMessage }}
				</UAlert>

				<div class="inline-elements">
					<p>Don't have an account?</p>
					<ULink to="/registrationPage" class="text-primary-500 underline"
						>Sign Up</ULink
					>
				</div>
			</UForm>
		</div>
	</div>
</template>

<script setup lang="ts">
import { object, string, boolean, type InferType } from 'yup';
import type { FormSubmitEvent } from '#ui/types';
import { useUserApi } from '~/composables/useUserApi';

const schema = object({
	email: string().email('Invalid email').required('Required'),
	password: string()
		.min(8, 'Must be at least 8 characters')
		.required('Required'),
	rememberMe: boolean(),
});

type Schema = InferType<typeof schema>;

const state = reactive({
	email: undefined,
	password: undefined,
	rememberMe: false,
});

const { loginUser } = useUserApi();
const router = useRouter();
// const authStore = useAuthStore();

const isLoading = ref(false);
const errorMessage = ref('');

async function onSubmit(event: FormSubmitEvent<Schema>) {
	isLoading.value = true;
	errorMessage.value = '';

	try {
		await loginUser(
			{
				email: event.data.email,
				password: event.data.password,
			},
			event.data.rememberMe ?? false
		);

		// Redirect to home page after successful login
		router.push('/');
	} catch (error) {
		console.error('Login failed:', error);
		if (error instanceof Error) {
			switch (error.message) {
				case 'Invalid credentials':
					errorMessage.value = 'Invalid email or password. Please try again.';
					break;
				case 'User not found':
					errorMessage.value =
						'No account found with this email. Please register.';
					break;
				case 'Network Error':
					errorMessage.value =
						'Unable to connect to the server. Please check your internet connection.';
					break;
				default:
					errorMessage.value =
						'An unexpected error occurred. Please try again later.';
			}
		} else {
			errorMessage.value =
				'An unexpected error occurred. Please try again later.';
		}
	} finally {
		isLoading.value = false;
	}
}
</script>

<style scoped>
.inline-elements {
	display: flex;
	align-items: center;
}

.inline-elements > :first-child {
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
