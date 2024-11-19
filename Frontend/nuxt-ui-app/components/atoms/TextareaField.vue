<template>
	<UFormGroup :label="label">
		<UTextarea v-model="localValue" :placeholder="placeholder" :rows="rows" />
	</UFormGroup>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, ref, watch } from 'vue';

const props = defineProps({
	modelValue: { type: [String, Array], default: '' },
	label: { type: String, default: '' },
	placeholder: { type: String, default: '' },
	rows: { type: Number, default: 3 }, // Number of rows in the textarea
});

const emit = defineEmits(['update:modelValue']);

// Local variable to manage textarea value
const localValue = ref(
	typeof props.modelValue === 'string' ? props.modelValue : ''
);

// Watch for changes to localValue and emit updates
watch(localValue, (newValue) => {
	emit('update:modelValue', newValue);
});

// Sync prop changes to local variable
watch(
	() => props.modelValue,
	(newValue) => {
		localValue.value = typeof newValue === 'string' ? newValue : '';
	}
);
</script>
