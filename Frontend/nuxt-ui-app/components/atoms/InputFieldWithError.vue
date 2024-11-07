<template>
	<UFormGroup :label="label" :name="name" class="mb-4">
		<UInput v-model="inputValue" v-bind="$attrs" />
		<p v-if="error" class="text-red-500 text-sm mt-1">{{ error }}</p>
	</UFormGroup>
</template>

<script setup lang="ts">
const props = defineProps<{
	label: string;
	name: string;
	modelValue: string | number;
	error?: string;
}>();

const emit = defineEmits(['update:modelValue']);

const inputValue = computed({
	get: () => props.modelValue,
	set: (value) => emit('update:modelValue', value),
});
</script>
