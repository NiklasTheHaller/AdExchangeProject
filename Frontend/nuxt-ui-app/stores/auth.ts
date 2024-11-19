import { defineStore } from 'pinia';

interface User {
	username: string;
	accountType: 'ADMIN' | 'PUBLISHER' | 'ADVERTISER';
}

export const useAuthStore = defineStore('auth', {
	state: () => ({
		token: null as string | null,
		user: null as User | null,
	}),
	getters: {
		isAuthenticated: (state) => !!state.token,
		username: (state) => state.user?.username,
		accountType: (state) => state.user?.accountType,
	},
	actions: {
		setToken(token: string, remember: boolean) {
			this.token = token;
			if (remember) {
				localStorage.setItem('token', token);
			}
		},
		setUser(user: User, remember: boolean) {
			this.user = user;
			if (remember) {
				localStorage.setItem('user', JSON.stringify(user));
			}
		},
		logout() {
			this.token = null;
			this.user = null;
			localStorage.removeItem('token');
			localStorage.removeItem('user');
		},
		initializeAuth() {
			const token = localStorage.getItem('token');
			const user = localStorage.getItem('user');
			if (token) this.token = token;
			if (user) this.user = JSON.parse(user);
		},
	},
});
