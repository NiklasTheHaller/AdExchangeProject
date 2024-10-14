<template>
	<div>
		<header>
			<nav>
				<NuxtLink to="/">Ad Exchange</NuxtLink>
				<ul>
					<li><NuxtLink to="/">Home</NuxtLink></li>
					<li><NuxtLink to="/registrationPage">Registration</NuxtLink></li>
				</ul>
			</nav>
		</header>

		<div>
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
