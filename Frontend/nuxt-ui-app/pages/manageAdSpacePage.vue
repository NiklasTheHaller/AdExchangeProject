<template>
	<div>
		<h1>Manage your ad spaces</h1>

		<div>
			<h2>Ad Spaces</h2>
			<UTable :columns="columns" :rows="adSpaces">
				<template #actions-data="{ row }">
					<UButton
						color="primary"
						variant="soft"
						icon="i-heroicons-pencil-square"
						size="sm"
						@click="editAdSpace(row)"
						>Edit</UButton
					>
					<UButton
						color="red"
						variant="soft"
						icon="i-heroicons-trash"
						size="sm"
						@click="deleteAdSpace(row.id)"
						>Delete</UButton
					>
				</template>
			</UTable>
		</div>
	</div>
</template>

<script lang="ts" setup>
const columns = [
	{
		key: 'id',
		label: 'ID',
	},
	{
		key: 'url',
		label: 'URL',
	},
	{
		key: 'keywords',
		label: 'Keywords',
	},
	{
		key: 'width',
		label: 'Width',
	},
	{
		key: 'height',
		label: 'Height',
	},
	{
		key: 'actions',
		label: 'Actions',
	},
];

const adSpaces = ref([
	{
		id: 1,
		url: 'www.sports4kids.com/',
		keywords: ['children', 'sports'],
		width: 720,
		height: 480,
	},
	{
		id: 2,
		url: 'www.techworld.com/',
		keywords: ['technology', 'gadgets'],
		width: 300,
		height: 250,
	},
	{
		id: 3,
		url: 'www.foodiesparadise.com/',
		keywords: ['food', 'recipes'],
		width: 600,
		height: 400,
	},
	{
		id: 4,
		url: 'www.traveladventures.com/',
		keywords: ['travel', 'adventure'],
		width: 800,
		height: 600,
	},
	{
		id: 5,
		url: 'www.fashionhub.com/',
		keywords: ['fashion', 'style'],
		width: 400,
		height: 300,
	},
]);

interface AdSpace {
	id: number;
	url: string;
	keywords: string[];
	width: number;
	height: number;
}

const editAdSpace = (adSpace: AdSpace) => {
	// Implement edit functionality
	console.log('Edit ad space:', adSpace);
};

const deleteAdSpace = (id: number) => {
	// Implement delete functionality
	console.log('Delete ad space:', id);
};
</script>

<style></style>
