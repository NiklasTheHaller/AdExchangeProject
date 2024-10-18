<template>
	<div>
		<header>
			<nav class="container mx-auto p-4 flex justify-between">
				<NuxtLink class="font-bold" to="/">Ad Exchange</NuxtLink>
				<ul class="flex gap-4">
					<li><NuxtLink to="/">Home</NuxtLink></li>
					<li><NuxtLink to="/registrationPage">Registration</NuxtLink></li>
				</ul>
			</nav>
		</header>

		<div class="container mx-auto p-4">
			<slot />
			<!-- Output Page Content -->
		</div>
	</div>
</template>

<style scoped>
.router-link-active {
	color: #12b488; /* Active item */
}
</style>
