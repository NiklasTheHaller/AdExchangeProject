<template>
  <div class="ad-test-page">
    <h1>{{ pageTitle }}</h1>
    <div class="content">
      <p>This is a sample page content. The advertisement will be displayed below.</p>
    </div>
    <!-- Ad Space -->
    <div
        v-if="adSpace"
        :id="adSpace.id || 'ad-space-placeholder'"
        class="ad-space"
        :style="{ width: `${adSpace.width}px`, height: `${adSpace.height}px` }"
    >
      <p v-if="!adLoaded">Loading advertisement...</p>
      <p v-else>
        {{ formatKeywords(adSpace.keywords) }}
      </p>
    </div>
    <!-- Error Handling -->
    <div v-else-if="error">
      <p>Error: {{ error }}</p>
    </div>
    <!-- Loading State -->
    <div v-else>
      <p>Loading Ad Space...</p>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router'; // Import useRoute to access query parameters
import axios from 'axios';

const route = useRoute(); // Access query parameters from the route
const pageTitle = "Test Page for OpenRTB Advertisement";
const adLoaded = ref(false);
const adSpace = ref(null);
const error = ref(null);

const fetchAdSpace = async (id) => {
  try {
    adLoaded.value = false; // Reset loading state
    const response = await axios.get(`http://localhost:8080/adspaces/${id}`);
    adSpace.value = response.data; // Populate ad space object
    adLoaded.value = true; // Mark as loaded
  } catch (err) {
    error.value = err.message || "Failed to fetch ad space data.";
    console.error("Error fetching AdSpace data:", err);
  }
};

// Format keywords to handle both string and array cases
const formatKeywords = (keywords) => {
  if (Array.isArray(keywords)) {
    return keywords.join(', ');
  }
  return keywords || "No Keywords Available";
};

onMounted(() => {
  const adSpaceId = route.query.id; // Extract `id` from query parameters
  if (adSpaceId) {
    fetchAdSpace(adSpaceId); // Fetch the ad dynamically using the query parameter
  } else {
    error.value = "AdSpace ID is missing in the query parameters.";
    console.error("AdSpace ID is undefined in the query parameters.");
  }
});
</script>


<style scoped>
.ad-test-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.content {
  margin-bottom: 20px;
}

.ad-space {
  background-color: #f0f0f0;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
  font-size: 16px;
  color: #333;
  text-align: center;
}
</style>
