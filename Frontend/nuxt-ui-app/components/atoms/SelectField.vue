<template>
	<UFormGroup :label="label">
		<USelect v-model="localValue" :options="options" :multiple="multiple" />
	</UFormGroup>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, ref, watch } from 'vue';

const props = defineProps({
	modelValue: { type: [String, Array], default: '' },
	label: { type: String, default: '' },
	options: { type: Array, default: () => [] },
	multiple: { type: Boolean, default: false },
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
