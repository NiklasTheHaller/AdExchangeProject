<template>
	<form @submit.prevent="saveAdSpace">
		<InputField v-model="formData.url" label="URL" input-type="text" />
		<SelectField
			v-model="formData.status"
			label="Status"
			:options="['active', 'inactive']" />
		<TextareaField
			v-model="keywordsText"
			label="Keywords"
			placeholder="Enter keywords separated by commas" />
		<UFormGroup label="Size">
			<div class="flex space-x-2">
				<UInput
					v-model.number="formData.size.width"
					type="number"
					placeholder="Width" />
				<UInput
					v-model.number="formData.size.height"
					type="number"
					placeholder="Height" />
			</div>
		</UFormGroup>
		<InputField
			v-model="formData.position"
			label="Position"
			input-type="text" />
		<SelectField
			v-model="formData.ad_types"
			label="Ad Types"
			:options="['banner', 'video', 'native']"
			multiple />
		<InputField
			v-model.number="formData.min_cpm"
			label="Min CPM"
			input-type="number" />
		<InputField
			v-model.number="formData.floor_price"
			label="Floor Price"
			input-type="number" />
		<TextareaField
			v-model="allowedAdvertisersText"
			label="Allowed Advertisers"
			placeholder="Enter allowed advertisers separated by commas" />
		<TextareaField
			v-model="blockedAdvertisersText"
			label="Blocked Advertisers"
			placeholder="Enter blocked advertisers separated by commas" />
		<TextareaField
			v-model="allowedCategoriesText"
			label="Allowed Categories"
			placeholder="Enter allowed categories separated by commas" />
		<TextareaField
			v-model="blockedCategoriesText"
			label="Blocked Categories"
			placeholder="Enter blocked categories separated by commas" />
		<InputField
			v-model.number="formData.viewability"
			label="Viewability"
			input-type="number" />
		<SelectField
			v-model="formData.device_types"
			label="Device Types"
			:options="['desktop', 'mobile', 'tablet']"
			multiple />
		<TextareaField
			v-model="geoTargetingText"
			label="Geo Targeting"
			placeholder="Enter geo targeting codes separated by commas" />
		<InputField
			v-model="formData.language"
			label="Language"
			input-type="text" />
		<CheckboxField v-model="formData.secure" label="Secure" />
		<div class="flex justify-end space-x-2 mt-4">
			<UButton color="gray" @click="$emit('cancel')">Cancel</UButton>
			<UButton color="primary" type="submit">Save</UButton>
		</div>
	</form>
</template>

<script setup lang="ts">
import { reactive, computed } from 'vue';
import type { AdSpace } from '../../types.ts';

const props = defineProps<{ adSpace?: AdSpace | null }>();

const emit = defineEmits(['save', 'cancel']);

// Reactive form data, initializing with either a blank object or the provided `adSpace` data
const formData = reactive<AdSpace>({
	id: props.adSpace?.id || 0,
	url: '',
	status: 'active',
	keywords: [],
	size: { width: 0, height: 0 },
	position: '',
	ad_types: [],
	min_cpm: 0,
	floor_price: 0,
	allowed_advertisers: [],
	blocked_advertisers: [],
	allowed_categories: [],
	blocked_categories: [],
	viewability: 0,
	earnings: 0,
	device_types: [],
	geo_targeting: [],
	language: '',
	secure: false,
	...(props.adSpace || {}),
});

// Computed properties to handle text-based inputs that need to be parsed into arrays
const keywordsText = computed({
	get: () => formData.keywords.join(', '),
	set: (value: string) => {
		formData.keywords = value
			.split(',')
			.map((k) => k.trim())
			.filter(Boolean);
	},
});

const allowedAdvertisersText = computed({
	get: () => formData.allowed_advertisers.join(', '),
	set: (value: string) => {
		formData.allowed_advertisers = value
			.split(',')
			.map((a) => a.trim())
			.filter(Boolean);
	},
});

const blockedAdvertisersText = computed({
	get: () => formData.blocked_advertisers.join(', '),
	set: (value: string) => {
		formData.blocked_advertisers = value
			.split(',')
			.map((a) => a.trim())
			.filter(Boolean);
	},
});

const allowedCategoriesText = computed({
	get: () => formData.allowed_categories.join(', '),
	set: (value: string) => {
		formData.allowed_categories = value
			.split(',')
			.map((c) => c.trim())
			.filter(Boolean);
	},
});

const blockedCategoriesText = computed({
	get: () => formData.blocked_categories.join(', '),
	set: (value: string) => {
		formData.blocked_categories = value
			.split(',')
			.map((c) => c.trim())
			.filter(Boolean);
	},
});

const geoTargetingText = computed({
	get: () => formData.geo_targeting.join(', '),
	set: (value: string) => {
		formData.geo_targeting = value
			.split(',')
			.map((g) => g.trim())
			.filter(Boolean);
	},
});

// Emit form data when saving
const saveAdSpace = () => {
	emit('save', { ...formData });
};
</script>
