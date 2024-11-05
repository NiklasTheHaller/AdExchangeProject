<template>
	<UFormGroup :label="label">
		<UCheckbox v-model="localValue" />
	</UFormGroup>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, ref, watch } from 'vue';

const props = defineProps({
	modelValue: Boolean,
	label: {
		type: String,
		default: '',
	},
});

const emit = defineEmits(['update:modelValue']);

const localValue = ref(props.modelValue);

watch(localValue, (newValue) => {
	emit('update:modelValue', newValue);
});

watch(
	() => props.modelValue,
	(newValue) => {
		localValue.value = newValue;
	}
);
</script>
