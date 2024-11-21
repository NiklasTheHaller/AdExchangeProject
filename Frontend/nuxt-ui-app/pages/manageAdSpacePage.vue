<template>
	<div>
		<h1 class="text-2xl font-bold mb-4">Manage your ad spaces</h1>
		<UButton color="primary" class="mb-4" @click="openAdSpaceModal()">
			Create new Ad Space
		</UButton>
		<UTable :columns="columns" :rows="adSpaces">
			<template #actions-data="{ row }">
				<UButton
					class="mr-2"
					color="orange"
					variant="soft"
					icon="i-heroicons-pencil-square"
					size="sm"
					@click="openAdSpaceModal(row)">
					Edit
				</UButton>
				<UButton
					color="red"
					variant="soft"
					icon="i-heroicons-trash"
					size="sm"
					@click="deleteAdSpace(row.id)">
					Delete
				</UButton>
			</template>
		</UTable>
		<UModal v-model="showAdSpaceModal" :ui="{ width: 'w-full sm:max-w-4xl' }">
			<UCard>
				<template #header>
					<h3 class="text-lg font-semibold">
						{{ editingAdSpace ? 'Edit Ad Space' : 'Create Ad Space' }}
					</h3>
				</template>
				<AdSpaceForm
					:ad-space="editingAdSpace"
					@save="saveAdSpace"
					@cancel="closeAdSpaceModal" />
			</UCard>
		</UModal>
	</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import type { AdSpace } from '../types';
import AdSpaceForm from '~/components/organisms/AdSpaceForm.vue';

const columns = [
	{ key: 'id', label: 'ID' },
	{ key: 'url', label: 'URL' },
	{ key: 'status', label: 'Status' },
	{ key: 'size', label: 'Size' },
	{ key: 'position', label: 'Position' },
	{ key: 'ad_types', label: 'Ad Types' },
	{ key: 'earnings', label: 'Earnings' },
	{ key: 'actions', label: 'Actions' },
];

const adSpaces = ref<AdSpace[]>([]);
const showAdSpaceModal = ref(false);
const editingAdSpace = ref<AdSpace | null>(null);

const fetchAdSpaces = async () => {
	// Simulated API call
	adSpaces.value = [
		{
			id: 1,
			url: 'www.example.com',
			status: 'active',
			keywords: ['sports', 'news'],
			size: { width: 300, height: 250 },
			position: 'above-the-fold',
			ad_types: ['banner'],
			min_cpm: 1.5,
			floor_price: 1.0,
			allowed_advertisers: [],
			blocked_advertisers: [],
			allowed_categories: ['IAB1', 'IAB2'],
			blocked_categories: [],
			viewability: 0.8,
			device_types: ['desktop', 'mobile'],
			geo_targeting: ['US', 'CA'],
			language: 'en',
			secure: true,
			earnings: 1000.5,
		},
		// Add more sample ad spaces here
	];
};

const openAdSpaceModal = (adSpace?: AdSpace) => {
	editingAdSpace.value = adSpace ? { ...adSpace } : null;
	showAdSpaceModal.value = true;
};

const closeAdSpaceModal = () => {
	showAdSpaceModal.value = false;
	editingAdSpace.value = null; // Ensure form is reset
};

const saveAdSpace = async (adSpaceData: AdSpace) => {
	// Simulated API call
	if (editingAdSpace.value) {
		const index = adSpaces.value.findIndex((as) => as.id === adSpaceData.id);
		if (index !== -1) {
			adSpaces.value[index] = adSpaceData;
		}
	} else {
		adSpaceData.id = Math.max(...adSpaces.value.map((as) => as.id), 0) + 1;
		adSpaces.value.push(adSpaceData);
	}
	closeAdSpaceModal();
};

const deleteAdSpace = async (id: number) => {
	// Simulated API call
	adSpaces.value = adSpaces.value.filter((as) => as.id !== id);
};

onMounted(fetchAdSpaces);
</script>
