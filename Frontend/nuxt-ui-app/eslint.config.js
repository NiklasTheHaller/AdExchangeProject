import { createConfigForNuxt } from '@nuxt/eslint-config/flat';

export default createConfigForNuxt({
	// Add any additional rules or configurations here
	rules: {
		'vue/multi-word-component-names': [
			'error',
			{
				ignores: ['default', 'index'],
			},
		],
	},
});
