// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
	devtools: { enabled: true },
	modules: ['@nuxt/ui', '@nuxtjs/eslint-module'],
	compatibilityDate: '2024-10-17',
	colorMode: {
		classSuffix: '',
		preference: 'system',
		fallback: 'dark',
	},
});
