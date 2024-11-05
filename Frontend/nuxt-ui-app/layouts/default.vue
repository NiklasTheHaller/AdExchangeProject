<script setup lang="ts">
import { useRouter, useColorMode } from '#imports';

const colorMode = useColorMode();
const router = useRouter();

const goToLogin = () => {
	router.push('/loginPage');
};
</script>

<template>
	<ClientOnly>
		<div :class="{ dark: colorMode.value === 'dark' }">
			<header
				class="bg-background/75 backdrop-blur border-b border-gray-200 dark:border-gray-800 -mb-px sticky top-0 z-50">
				<nav class="container mx-auto p-4 flex justify-between items-center">
					<NuxtLink
						class="flex-shrink-0 font-bold text-xl text-gray-900 dark:text-white inline-flex items-center"
						to="/"
						active-class="router-link-active">
						<span class="text-gray-800 dark:text-white">Ad</span>
						<span class="text-primary-500">Exchange</span>
					</NuxtLink>
					<ul class="flex gap-4 items-center">
						<li>
							<NuxtLink
								:class="
									colorMode.value === 'dark' ? 'text-gray-300' : 'text-gray-900'
								"
								to="/"
								active-class="router-link-active">
								Home
							</NuxtLink>
						</li>
						<li>
							<NuxtLink
								:class="
									colorMode.value === 'dark' ? 'text-gray-300' : 'text-gray-900'
								"
								to="/manageAdSpacePage"
								active-class="router-link-active">
								Manage Ad Space
							</NuxtLink>
						</li>
						<li>
							<NuxtLink
								:class="
									colorMode.value === 'dark' ? 'text-gray-300' : 'text-gray-900'
								"
								to="/registrationPage"
								active-class="router-link-active">
								Registration
							</NuxtLink>
						</li>
						<li v-if="$route.path !== '/loginPage'">
							<UButton
								:class="
									colorMode.value === 'dark'
										? 'bg-primary text-white'
										: 'bg-black text-white'
								"
								@click="goToLogin">
								Login
							</UButton>
						</li>
						<li>
							<UButton
								:icon="
									colorMode.value === 'dark'
										? 'i-heroicons-moon-20-solid'
										: 'i-heroicons-sun-20-solid'
								"
								color="gray"
								variant="ghost"
								aria-label="Theme"
								@click="
									colorMode.preference =
										colorMode.value === 'dark' ? 'light' : 'dark'
								" />
						</li>
					</ul>
				</nav>
			</header>

			<div class="container mx-auto p-4">
				<slot />
			</div>
		</div>
	</ClientOnly>
</template>

<style lang="postcss" scoped>
.router-link-active {
	@apply text-primary-500 dark:text-primary-300;
}
</style>
