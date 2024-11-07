// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
	devtools: { enabled: true },
	runtimeConfig: {
		public: {
			apiBase: 'https://localhost:5432/users',
		},
	},
	modules: ['@nuxt/ui', '@nuxtjs/eslint-module'],
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
