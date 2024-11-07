<template>
	<UFormGroup :label="label">
		<UInput v-model="localValue" :type="inputType" :placeholder="placeholder" />
	</UFormGroup>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, ref, watch } from 'vue';

const props = defineProps({
	modelValue: { type: [String, Number], default: '' },
	label: { type: String, default: '' },
	inputType: { type: String, default: 'text' },
	placeholder: { type: String, default: '' },
});

const emit = defineEmits(['update:modelValue']);

// Local variable to manage input
const localValue = ref(props.modelValue);

// Watch for changes to localValue and emit updates
watch(localValue, (newValue) => {
	emit('update:modelValue', newValue);
});

// Sync prop changes to local variable
watch(
	() => props.modelValue,
	(newValue) => {
		localValue.value = newValue;
	}
);
</script>
