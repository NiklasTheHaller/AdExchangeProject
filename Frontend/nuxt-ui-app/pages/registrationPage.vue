<template>
	<div>
		<h1 class="text-2xl font-bold mb-6">Registration</h1>
		<form class="max-w-md mx-auto" @submit.prevent="handleSubmit">
			<URadioGroup
				v-model.trim="form.accountType"
				class="mb-4"
				legend="Account Type"
				:options="accountOptions" />
			<p v-if="errors.accountType" class="text-red-500 text-sm mt-1">
				{{ errors.accountType }}
			</p>

			<UFormGroup label="Username" name="username" class="mb-4">
				<UInput
					v-model.trim="form.username"
					placeholder="Choose a unique username" />
				<p v-if="errors.username" class="text-red-500 text-sm mt-1">
					{{ errors.username }}
				</p>
			</UFormGroup>

			<UFormGroup label="Email" name="email" class="mb-4">
				<UInput
					v-model.trim="form.email"
					type="email"
					placeholder="Enter your email" />
				<p v-if="errors.email" class="text-red-500 text-sm mt-1">
					{{ errors.email }}
				</p>
			</UFormGroup>

			<UFormGroup label="Confirm Email" name="confirmEmail" class="mb-4">
				<UInput
					v-model.trim="form.confirmEmail"
					type="email"
					placeholder="Confirm your email" />
				<p v-if="errors.confirmEmail" class="text-red-500 text-sm mt-1">
					{{ errors.confirmEmail }}
				</p>
			</UFormGroup>

			<UFormGroup label="Password" name="password" class="mb-4">
				<UInput
					v-model="form.password"
					type="password"
					placeholder="Choose a strong password" />
				<p class="text-sm text-gray-600 mt-1">
					Password must be at least 8 characters long, contain one uppercase
					letter, and one number or special character.
				</p>
				<p v-if="errors.password" class="text-red-500 text-sm mt-1">
					{{ errors.password }}
				</p>
			</UFormGroup>

			<UFormGroup label="Confirm Password" name="confirmPassword" class="mb-6">
				<UInput
					v-model="form.confirmPassword"
					type="password"
					placeholder="Confirm your password" />
				<p v-if="errors.confirmPassword" class="text-red-500 text-sm mt-1">
					{{ errors.confirmPassword }}
				</p>
			</UFormGroup>

			<UButton
				type="submit"
				color="primary"
				class="w-full"
				:loading="isLoading"
				:disabled="isLoading">
				{{ isLoading ? 'Registering...' : 'Register' }}
			</UButton>
		</form>
	</div>
</template>

<script setup>
import * as Yup from 'yup';

const accountOptions = [
	{ label: 'Advertiser', value: 'advertiser' },
	{ label: 'Publisher', value: 'publisher' },
];

const isLoading = ref(false);

const form = reactive({
	accountType: '',
	username: '',
	email: '',
	confirmEmail: '',
	password: '',
	confirmPassword: '',
});

const errors = reactive({
	username: '',
	email: '',
	confirmEmail: '',
	password: '',
	confirmPassword: '',
});

const validationSchema = Yup.object().shape({
	accountType: Yup.string().required('Account type is required'),
	username: Yup.string().required('Username is required'),
	email: Yup.string().email('Invalid email').required('Email is required'),
	confirmEmail: Yup.string()
		.oneOf([Yup.ref('email'), null], 'Emails must match')
		.required('Confirm Email is required'),
	password: Yup.string()
		.matches(
			/^(?=.*[A-Z])(?=.*[\d\W]).{8,}$/,
			'Password must be at least 8 characters long, contain one uppercase letter, and one number or special character'
		)
		.required('Password is required'),
	confirmPassword: Yup.string()
		.oneOf([Yup.ref('password'), null], 'Passwords must match')
		.required('Confirm Password is required'),
});

const validateField = async (field) => {
	try {
		errors[field] = ''; // Clear the error message for the field
		await validationSchema.validateAt(field, form);
	} catch (e) {
		errors[field] = e.message; // Set the error message for the field
	}
};

// Individual watchers for each form field
watch(
	() => form.accountType,
	() => validateField('accountType')
);
watch(
	() => form.username,
	() => validateField('username')
);
watch(
	() => form.email,
	() => validateField('email')
);
watch(
	() => form.confirmEmail,
	() => validateField('confirmEmail')
);
watch(
	() => form.password,
	() => validateField('password')
);
watch(
	() => form.confirmPassword,
	() => validateField('confirmPassword')
);

const handleSubmit = async () => {
	try {
		// Clear all error messages
		Object.keys(errors).forEach((key) => (errors[key] = ''));

		// Validate the entire form
		await validationSchema.validate(form, { abortEarly: false });
		isLoading.value = true;

		const response = await $fetch(
			'https://jsonplaceholder.typicode.com/users',
			{
				method: 'POST',
				body: form,
			}
		);

		if (!response.ok) throw new Error('Registration failed');

		alert(`Registration successful! Welcome, ${form.username}`);
		resetForm();
	} catch (e) {
		if (e instanceof Yup.ValidationError) {
			e.inner.forEach((error) => {
				errors[error.path] = error.message;
			});
		} else {
			alert(e.message || 'Something went wrong');
		}
	} finally {
		isLoading.value = false;
	}
};

const resetForm = () => {
	Object.keys(form).forEach((key) => (form[key] = ''));
	Object.keys(errors).forEach((key) => (errors[key] = ''));
};
</script>
