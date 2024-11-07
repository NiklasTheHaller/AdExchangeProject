<template>
	<form class="max-w-md mx-auto" @submit.prevent="handleSubmit">
		<RadioGroup
			v-model="form.accountType"
			legend="Account Type"
			:options="accountOptions"
			:error="errors.accountType" />
		<InputFieldWithError
			v-model="form.username"
			label="Username"
			name="username"
			placeholder="Choose a unique username"
			:error="errors.username" />
		<InputFieldWithError
			v-model="form.email"
			label="Email"
			name="email"
			type="email"
			placeholder="Enter your email"
			:error="errors.email" />
		<InputFieldWithError
			v-model="form.confirmEmail"
			label="Confirm Email"
			name="confirmEmail"
			type="email"
			placeholder="Confirm your email"
			:error="errors.confirmEmail" />
		<PasswordField
			v-model="form.password"
			label="Password"
			name="password"
			placeholder="Choose a strong password"
			:error="errors.password"
			hint="Password must be at least 8 characters long, contain one uppercase letter, and one number or special character." />
		<PasswordField
			v-model="form.confirmPassword"
			label="Confirm Password"
			name="confirmPassword"
			placeholder="Confirm your password"
			:error="errors.confirmPassword" />
		<SubmitButton
			text="Register"
			loading-text="Registering..."
			:loading="isLoading" />
	</form>
</template>

<script setup>
import * as Yup from 'yup';
import { useApi } from '../composables/useApi';

const { registerUser } = useApi();

const accountOptions = ref([
	{ label: 'Advertiser', value: 'advertiser' },
	{ label: 'Publisher', value: 'publisher' },
]);

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
		errors[field] = '';
		await validationSchema.validateAt(field, form);
	} catch (e) {
		errors[field] = e.message;
	}
};

// Individual watchers for each form field
Object.keys(form).forEach((key) =>
	watch(
		() => form[key],
		() => validateField(key)
	)
);

const handleSubmit = async () => {
	try {
		Object.keys(errors).forEach((key) => (errors[key] = ''));
		await validationSchema.validate(form, { abortEarly: false });
		isLoading.value = true;

		// Only send required fields to backend
		const formData = {
			username: form.username,
			email: form.email,
			password: form.password,
			accountType: form.accountType.toUpperCase(),
		};

		await registerUser(formData);
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
