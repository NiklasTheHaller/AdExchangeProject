import { useAuthStore } from '~/stores/auth';

interface RegisterUserData {
	username: string;
	email: string;
	password: string;
	accountType: 'ADMIN' | 'PUBLISHER' | 'ADVERTISER';
}

interface LoginUserData {
	username?: string;
	email?: string;
	password: string;
}

interface ApiError {
	data?: { message?: string };
}

export function useUserApi() {
	const config = useRuntimeConfig();
	const userApiBase = config.public.userApiBase;
	const authStore = useAuthStore();

	// Register user
	const registerUser = async (formData: RegisterUserData) => {
		try {
			const response = await $fetch('/register', {
				baseURL: userApiBase,
				method: 'POST',
				body: formData,
			});
			return response;
		} catch (error: unknown) {
			const apiError = error as ApiError;
			throw new Error(apiError.data?.message || 'Registration failed');
		}
	};

	const loginUser = async (loginData: LoginUserData, rememberMe: boolean) => {
		try {
			const response = await $fetch<{
				token: string;
				user: { username: string; accountType: string };
			}>('/login', {
				baseURL: userApiBase,
				method: 'POST',
				body: loginData,
			});

			// Store the token and user info
			authStore.setToken(response.token, rememberMe);
			authStore.setUser(
				{
					username: response.user.username,
					accountType: response.user.accountType as
						| 'ADMIN'
						| 'PUBLISHER'
						| 'ADVERTISER',
				},
				rememberMe
			);

			return response;
		} catch (error: unknown) {
			const apiError = error as ApiError;
			throw new Error(apiError.data?.message || 'Login failed');
		}
	};

	return { registerUser, loginUser };
}