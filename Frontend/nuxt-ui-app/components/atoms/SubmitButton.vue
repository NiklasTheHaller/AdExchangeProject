<template>
	<UButton
		type="submit"
		color="primary"
		class="w-full"
		:loading="loading"
		:disabled="loading">
		{{ loading ? loadingText : text }}
	</UButton>
</template>

<script setup lang="ts">
defineProps<{
	text: string;
	loadingText: string;
	loading: boolean;
}>();
</script>
