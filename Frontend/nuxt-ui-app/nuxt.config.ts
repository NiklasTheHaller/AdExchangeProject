// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
	devtools: { enabled: true },
	runtimeConfig: {
		public: {
			userApiBase: 'http://localhost:8080/users',
		},
	},
	modules: ['@nuxt/ui', '@nuxtjs/eslint-module', '@pinia/nuxt'],
	compatibilityDate: '2024-10-17',
	colorMode: {
		classSuffix: '',
		preference: 'system',
		fallback: 'dark',
	},
	components: [
		{
			path: '~/components',
			pathPrefix: false,
		},
	],
});
