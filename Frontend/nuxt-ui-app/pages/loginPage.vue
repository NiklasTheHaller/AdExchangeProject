<template>
  <div>
    <div class="centerDiv max-w-md mx-auto">
      <h1 class="text-2xl font-bold mb-6">Sign In to Ad Exchange</h1>

      <UForm
        :schema="schema"
        :state="state"
        class="space-y-4"
        @submit="onSubmit"
      >
        <UFormGroup label="Email" name="email" size="xl">
          <UInput
            v-model="state.email"
            type="email"
            placeholder="you@example.com"
            icon="i-heroicons-envelope"
            :error="errors.email"
            :aria-describedby="'emailError'"
          />
          <p v-if="errors.email" id="emailError" class="text-red-500 text-sm">
            {{ errors.email }}
          </p>
        </UFormGroup>

        <UFormGroup label="Password" name="password" size="xl">
          <template #hint>
            <NuxtLink to="/reset-password" class="text-primary-500 underline"
              >Forgot?</NuxtLink
            >
          </template>

          <UInput
            v-model="state.password"
            type="password"
            placeholder="Enter Password"
            icon="i-heroicons-lock-closed"
            :error="errors.password"
            :aria-describedby="'passwordError'"
          />
          <p
            v-if="errors.password"
            id="passwordError"
            class="text-red-500 text-sm"
          >
            {{ errors.password }}
          </p>
        </UFormGroup>

        <UFormGroup name="rememberMe" class="flex items-center">
          <UCheckbox v-model="state.rememberMe" label="Remember me" />
        </UFormGroup>

        <UButton
          type="submit"
          size="xl"
          class="login-button"
          :loading="isLoading"
          :disabled="isLoading"
        >
          {{ isLoading ? "Signing In..." : "Sign In" }}
        </UButton>

        <UAlert
          v-if="errorMessage"
          color="red"
          icon="i-heroicons-exclamation-circle"
          class="mt-4"
          :aria-live="'assertive'"
        >
          {{ errorMessage }}
        </UAlert>

        <div class="inline-elements">
          <p>Don't have an account?</p>
          <ULink to="/registrationPage" class="text-primary-500 underline"
            >Sign Up</ULink
          >
        </div>
      </UForm>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ValidationError, object, string, boolean, type InferType } from "yup";
import type { FormSubmitEvent } from "#ui/types";
import { useUserApi } from "~/composables/useUserApi";
import { useAuthStore } from "~/stores/auth";

const schema = object({
  email: string().email("Invalid email").required("Email is required"),
  password: string()
    .min(8, "Password must be at least 8 characters")
    .required("Password is required"),
  rememberMe: boolean(),
});

type Schema = InferType<typeof schema>;

const state = reactive({
  email: "",
  password: "",
  rememberMe: false,
});

const errors = reactive({
  email: "",
  password: "",
});

const { loginUser } = useUserApi();
const router = useRouter();
const authStore = useAuthStore();

const isLoading = ref(false);
const errorMessage = ref("");

function validateForm() {
  try {
    schema.validateSync(state, { abortEarly: false });
    errors.email = "";
    errors.password = "";
    return true;
  } catch (validationErrors) {
    if (validationErrors instanceof ValidationError) {
      validationErrors.inner.forEach((error) => {
        if (error.path === "email") {
          errors.email = error.message;
        } else if (error.path === "password") {
          errors.password = error.message;
        }
      });
    }
    return false;
  }
}

async function onSubmit(event: FormSubmitEvent<Schema>) {
  isLoading.value = true;
  errorMessage.value = "";

  if (!validateForm()) {
    isLoading.value = false;
    return;
  }

  try {
    const response = await loginUser(
      {
        email: event.data.email,
        password: event.data.password,
      },
      event.data.rememberMe ?? false
    );

    if (response && response.Token) {
      authStore.setToken(response.Token, event.data.rememberMe ?? false);
      router.push("/");
    } else {
      throw new Error("Invalid response from server");
    }
  } catch (error) {
    console.error("Login failed:", error);
    if (error instanceof Error) {
      errorMessage.value = error.message;
      console.log(error.message);
    } else if (typeof error === "string") {
      errorMessage.value = error;
      console.log(error);
    } else {
      errorMessage.value =
        "An unexpected error occurred. Please try again later.";
    }
  } finally {
    isLoading.value = false;
  }
}
</script>

<style scoped>
.inline-elements {
  display: flex;
  align-items: center;
}

.inline-elements > :first-child {
  margin-right: 6px;
}

.centerDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 90%;
}

.login-button {
  width: 100%;
  font-weight: bold;
  font-size: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px !important;
}
</style>
