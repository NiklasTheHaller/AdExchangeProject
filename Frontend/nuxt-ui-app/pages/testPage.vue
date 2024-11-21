<template>
  <div class="ad-test-page">
    <h1>{{ pageTitle }}</h1>
    <div class="content">
      <p>This is a sample page content. The advertisement will be displayed below.</p>
    </div>
    <div 
      :id="adSpace.id" 
      class="ad-space" 
      :style="{ width: adSpace.size.width + 'px', height: adSpace.size.height + 'px' }"
    >
      <p v-if="!adLoaded">Loading advertisement...</p>
      <!-- The actual ad will be inserted here by the backend -->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const pageTitle = "Test Page for OpenRTB Advertisement";
const adLoaded = ref(false);

const adSpace = {
  id: "ad-space-001",
  url: "https://example.com/test-page",
  status: "active",
  keywords: ["test", "openrtb", "advertisement"],
  size: { width: 300, height: 250 },
  position: "above-the-fold",
  ad_types: ["banner"],
  min_cpm: 1.0,
  floor_price: 0.5,
  allowed_advertisers: ["example-advertiser.com"],
  blocked_advertisers: ["blocked-advertiser.com"],
  allowed_categories: ["IAB-1", "IAB-2"],
  blocked_categories: ["IAB-3"],
  viewability: 0.8,
  device_types: ["desktop", "mobile", "tablet"],
  geo_targeting: { country: "US" },
  language: "en",
  secure: 1
};

onMounted(() => {
  // Simulate ad loading
  setTimeout(() => {
    adLoaded.value = true;
    // The ad request is triggered here
    // The response is handled in the backend
  }, 2000);
});
</script>

<style scoped>
.ad-test-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
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
}
</style>
