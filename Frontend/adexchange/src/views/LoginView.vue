<template>
	<form>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">Email address</label>
			<input
				v-model="emailValue"
				type="email"
				class="form-control"
				id="exampleInputEmail1"
				aria-describedby="emailHelp" />
			<div id="emailHelp" class="form-text">
				We'll never share your email with anyone else.
			</div>
		</div>
		<div class="mb-3">
			<label for="exampleInputPassword1" class="form-label">Password</label>
			<input
				v-model="passwordValue"
				type="password"
				class="form-control"
				id="exampleInputPassword1" />
		</div>
		<div class="mb-3 form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1" />
			<label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		<button
			v-on:click="submitForm"
			id="submit"
			type="submit"
			class="btn btn-primary">
			Submit
		</button>
	</form>

	<div></div>

	<div id="results" v-if="!errorValue">
		<pre>
			{{ resultData }}
		</pre
		>
	</div>

	<div id="error" v-if="errorValue">
		{{ errorValue }}
	</div>
</template>

<script>
export default {
	name: 'LoginView2',
	data() {
		return {
			emailValue: '',
			passwordValue: '',
			resultData: '',
			errorValue: '',
		};
	},
	methods: {
		async submitForm(event) {
			event.preventDefault();
			console.log('Button clicked');
			try {
				const response = await fetch(
					'https://jsonplaceholder.typicode.com/users',
					{
						method: 'POST',
						body: JSON.stringify({
							email: this.emailValue,
							password: this.passwordValue,
						}),
						headers: {
							'Content-type': 'application/json; charset=UTF-8',
						},
					}
				);
				const data = await response.json();
				this.resultData = JSON.stringify(data, null, 2);
			} catch (error) {
				this.errorValue = error.message;
			}
		},
	},
	components: {},
};
</script>
