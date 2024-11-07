export function useApi() {
	const config = useRuntimeConfig();
	const baseURL = config.public.apiBase;

	const registerUser = async (formData: RegisterUserData) => {
		try {
			const response = await $fetch('/register', {
				baseURL,
				method: 'POST',
				body: formData,
			});
			return response;
		} catch (error: unknown) {
			const apiError = error as ApiError;
			throw new Error(apiError.data?.message || 'Registration failed');
		}
	};

	return { registerUser };
}

interface RegisterUserData {
	username: string;
	email: string;
	password: string;
	accountType: 'ADMIN' | 'PUBLISHER' | 'ADVERTISER';
}

interface ApiError {
	data?: { message?: string };
}
