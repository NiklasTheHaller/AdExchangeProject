<template>
	<UFormGroup :label="label">
		<component
			:is="componentType"
			v-model="modelValue"
			:placeholder="$attrs.placeholder"
			:options="options"
			:multiple="multiple"
			:type="inputType" />
	</UFormGroup>
</template>

<script setup lang="ts">
type InputType = 'text' | 'number' | 'email' | 'password';

interface Props {
	label: string;
	modelValue: string | number | boolean | string[];
	type: 'input' | 'select' | 'textarea' | 'checkbox';
	options?: string[];
	multiple?: boolean;
	inputType?: InputType;
}

const props = withDefaults(defineProps<Props>(), {
	options: () => [],
	multiple: false,
	inputType: 'text',
});

const emit = defineEmits<{
	(e: 'update:modelValue', value: string | number | boolean | string[]): void;
}>();

const componentType = computed(() => {
	switch (props.type) {
		case 'input':
			return 'UInput';
		case 'select':
			return 'USelect';
		case 'textarea':
			return 'UTextarea';
		case 'checkbox':
			return 'UCheckbox';
		default:
			return 'UInput';
	}
});

const modelValue = computed({
	get: () => props.modelValue,
	set: (value: string | number | boolean | string[]) =>
		emit('update:modelValue', value),
});
</script>
