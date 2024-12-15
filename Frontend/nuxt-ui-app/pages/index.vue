<template>
	<div class="min-h-screen bg-white dark:bg-gray-950 text-gray-900 dark:text-gray-100">
	  <!-- Hero Section -->
	  <section class="relative text-center py-16 px-4">
		<div class="container mx-auto max-w-4xl">
		  <h1 class="text-4xl md:text-5xl font-bold mb-4 text-primary-600 dark:text-primary-400">
			AdExchange
		  </h1>
		  <p class="text-lg md:text-xl mb-8 text-gray-600 dark:text-gray-300">
			Revolutionizing Digital Advertising with Real-Time Bidding
		  </p>
		  <div class="space-x-4">
			<UButton 
			  color="primary" 
			  size="lg" 
			  class="shadow-md hover:shadow-lg transition-all"
			>
			  Get Started
			</UButton>
			<UButton 
			  color="primary" 
			  variant="outline" 
			  size="lg" 
			  class="shadow-md hover:shadow-lg transition-all"
			>
			  Learn More
			</UButton>
		  </div>
		</div>
	  </section>
  
	  <!-- Why Choose AdExchange -->
	  <section class="py-16 px-4">
		<UContainer>
		  <h2 class="text-3xl font-bold text-center mb-12 text-gray-800 dark:text-gray-100">
			Why Choose AdExchange?
		  </h2>
		  <div class="grid md:grid-cols-2 gap-8">
			<UCard 
			  class="p-6 bg-white dark:bg-gray-900 border border-gray-200 dark:border-gray-800 shadow-sm hover:shadow-md transition-all"
			>
			  <h3 class="text-xl font-semibold mb-4 text-primary-600 dark:text-primary-400">
				For Publishers
			  </h3>
			  <ul class="space-y-2 text-gray-600 dark:text-gray-300">
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Maximize Ad Revenue
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Flexible Monetization
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Granular Control
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Advanced Targeting
				</li>
			  </ul>
			</UCard>
			<UCard 
			  class="p-6 bg-white dark:bg-gray-900 border border-gray-200 dark:border-gray-800 shadow-sm hover:shadow-md transition-all"
			>
			  <h3 class="text-xl font-semibold mb-4 text-primary-600 dark:text-primary-400">
				For Advertisers
			  </h3>
			  <ul class="space-y-2 text-gray-600 dark:text-gray-300">
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Precise Targeting
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Real-Time Bidding
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Cost-Effective
				</li>
				<li class="flex items-center">
				  <UIcon name="i-heroicons-check-circle" class="mr-2 text-green-500" />
				  Comprehensive Inventory
				</li>
			  </ul>
			</UCard>
		  </div>
		</UContainer>
	  </section>
  
	  <!-- How It Works -->
	  <section class="py-16 px-4">
		<UContainer>
		  <h2 class="text-3xl font-bold text-center mb-12 text-gray-800 dark:text-gray-100">
			How It Works
		  </h2>
		  <div class="max-w-2xl mx-auto">
			<ol class="space-y-4 text-lg text-gray-600 dark:text-gray-300">
			  <li class="flex items-start">
				<span class="mr-4 text-primary-600 dark:text-primary-400 font-bold">1.</span>
				<span>Publishers list their available ad spaces</span>
			  </li>
			  <li class="flex items-start">
				<span class="mr-4 text-primary-600 dark:text-primary-400 font-bold">2.</span>
				<span>Advertisers bid in real-time</span>
			  </li>
			  <li class="flex items-start">
				<span class="mr-4 text-primary-600 dark:text-primary-400 font-bold">2.</span>
				<span>The highest-quality, most relevant ad is selected instantly</span>
			  </li>
			  <li class="flex items-start">
				<span class="mr-4 text-primary-600 dark:text-primary-400 font-bold">4.</span>
				<span>Ads are displayed seamlessly to the end-user</span>
			  </li>
			</ol>
		  </div>
		</UContainer>
	  </section>
  
	  <!-- Call to Action -->
	  <section class="py-16 px-4 text-center">
		<div class="max-w-xl mx-auto">
		  <h3 class="text-2xl md:text-3xl font-bold mb-4 text-gray-800 dark:text-gray-100">
			Ready to Transform Your Digital Advertising?
		  </h3>
		  <p class="text-base md:text-lg mb-8 text-gray-600 dark:text-gray-300">
			Whether you're a publisher looking to monetize or an advertiser seeking precise targeting, we have you covered.
		  </p>
		  <div class="space-x-4">
			<UButton 
			  color="primary" 
			  size="lg" 
			  class="shadow-md hover:shadow-lg transition-all"
			>
			  Get Started
			</UButton>
			<UButton 
			  color="primary" 
			  variant="outline" 
			  size="lg" 
			  class="shadow-md hover:shadow-lg transition-all"
			>
			  Learn More
			</UButton>
		  </div>
		</div>
	  </section>
	</div>
  </template>
  
  <script setup>
  definePageMeta({
	layout: "default",
  });
  
  // Optional: Dark mode toggle
  const colorMode = useColorMode();
  
  const toggleDarkMode = () => {
	colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark';
  };
  </script>
  