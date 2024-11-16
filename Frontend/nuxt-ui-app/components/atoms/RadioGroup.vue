<template>
	<URadioGroup
		v-model="selectedValue"
		:legend="legend"
		:options="options"
		class="mb-4" />
	<p v-if="error" class="text-red-500 text-sm mt-1">{{ error }}</p>
</template>

<script setup lang="ts">
const props = defineProps<{
	modelValue: string;
	legend: string;
	options: { label: string; value: string }[];
	error?: string;
}>();

const emit = defineEmits(['update:modelValue']);

const selectedValue = computed({
	get: () => props.modelValue,
	set: (value) => emit('update:modelValue', value),
});
</script>
