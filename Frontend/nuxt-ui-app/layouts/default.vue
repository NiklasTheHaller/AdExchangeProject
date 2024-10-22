<script setup lang="ts">
const colorMode = useColorMode()
const isDark = computed({
	get() {
		return colorMode.value === 'dark'
	},
	set() {
		colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark'
	}
})

const goToLogin = () => {
	const router = useRouter()
	router.push('/loginPage')
}
</script>

<template>
	<div>
		<header
			class="bg-background/75 backdrop-blur border-b border-gray-200 dark:border-gray-800 -mb-px sticky top-0 z-50">
			<nav class="container mx-auto p-4 flex justify-between items-center">
				<NuxtLink 
				class="flex-shrink-0 font-bold text-xl text-gray-900 dark:text-white inline-flex items-center"
					to="/">
					<span class="text-gray-800 dark:text-white">Ad</span>
					<span class="text-primary-500">Exchange</span>
				</NuxtLink>
				<ul class="flex gap-4 items-center">
					<li>
						<NuxtLink to="/">Home</NuxtLink>
					</li>

					<li>
						<NuxtLink to="/manageAdSpacePage">Blog</NuxtLink>
					</li>

					<li>
						<NuxtLink to="/registrationPage">What's new?</NuxtLink>
					</li>

					<li v-if="$route.path !== '/loginPage'">
						<UButton :class="isDark ? 'bg-primary' : 'bg-black text-white'" @click="goToLogin">
							Login
						</UButton>
					</li>

					<!--<li>
						<NuxtLink to="/manageAdSpacePage">Manage Ad Space</NuxtLink>
					</li>-->

					<li>
						<ClientOnly>
							<UButton 
							:icon="isDark ? 'i-heroicons-moon-20-solid' : 'i-heroicons-sun-20-solid'"
								color="gray" variant="ghost" aria-label="Theme" @click="isDark = !isDark" />
							<template #fallback>
								<div class="w-8 h-8" />
							</template>
						</ClientOnly>
					</li>
				</ul>
			</nav>
		</header>

		<div class="container mx-auto p-4">
			<slot />
			<!-- Output Page Content -->
		</div>
	</div>
</template>

<style lang="postcss" scoped>
.router-link-active {
	@apply text-primary-500;
}
</style>
