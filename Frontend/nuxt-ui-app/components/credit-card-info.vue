<script lang="ts" setup>

const props = defineProps({
  cardholder: {
    type: String,
    required: true
  },
  cardnumber: {
    type: String,
    required: true
  },
  expire_month: {
    type: String,
    required: true
  },
  expire_year: {
    type: String,
    required: true
  },
  cvv: {
    type: String,
    required: true
  }
})

const card = reactive({
  brand: 'Mastercard',
  icon: 'logos:mastercard'
})

function discoverCardBrand(cardNumber: string) {

  const number = cardNumber.replace(/\D/g, '')

  const regexes = [
    { pattern: /^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$/, brand: 'Mastercard' },
    { pattern: /^(4[0-9]{12}(?:[0-9]{3})?)$/, brand: 'Visa' },
    { pattern: /^(3[4,7][0-9]{13})$/, brand: 'American Express' },
    { pattern: /^(6011|622(12[6-9]|1[3-9][0-9]|[2-8][0-9]{2}|9[0-1])|64[4-9][0-9]{3}|65[0-9]{3}|(222[1-9][0-9]{10}|22[3-9][0-9]{11}|2[3-6][0-9]{12}|27[01][0-9]{12}|2720[0-9]{10}))$/, brand: 'Discover' },
    { pattern: /^(5018|5020|5038|6304|6759|6761|6762|6763|0604|6390)$/, brand: 'Maestro' },
    { pattern: /^(9792|9795|564182|633110|677136|506699|633383|636297)$/, brand: 'Diners Club' },
    { pattern: /^(30[0-5][0-9]{11}|3095990189[0-9]{10}|3[3,4][0-9]{13}|35\d{14}|3528\d{12}|352900\d{10}|353[0-8]\d{11}|354[0-9]{12}|355[0-9]{12}|356[0-9]{12}|357[0-9]{12}|358[0-9]{12}|359[0-9]{12})$/, brand: 'JCB' },
  ]

  for (const { pattern, brand } of regexes) {
    if (pattern.test(number)) {
        console.log(brand)
      return brand
    }
  }

  return 'Unknown'
}

watchEffect(() => {
  card.brand = discoverCardBrand(props.cardnumber)

  switch (card.brand) {
    case 'Unknown':
      card.icon = 'logos:mastercard'
      break
    case 'Mastercard':
      card.icon = 'logos:mastercard'
      break
    case 'Visa':
      card.icon = 'logos:visa'
      break
    case 'American Express':
      card.icon = 'fontisto:american-express'
      break
    case 'Discover':
      card.icon = 'logos:discover'
      break
    case 'Maestro':
      card.icon = 'logos:maestro'
      break
    case 'Diners Club':
      card.icon = 'logos:diners-club'
      break
    case 'JCB':
      card.icon = 'logos:jcb'
      break
    default:
      card.icon = 'logos:mastercard'
  }
})

</script>

<template>
  <div
    class="dark:bg-muted-900 border-muted-200 dark:border-muted-800 shadow-muted-400/10 dark:shadow-muted-800/10 relative mx-auto h-[200px] w-full max-w-[315px] rounded-xl border p-6 ">
    <div class="flex h-full flex-col gap-3">
      <div class="flex items-center gap-2">
        <div class="bg-muted-200 dark:bg-muted-700 size-2 rounded-full"></div><span
          class="text-muted-400 font-sans text-sm"> {{ card.brand === 'Unknown' ? 'Mastercard' : card.brand }}
        </span>
      </div>
      <div class="mt-auto space-y-1"><img class="mb-3 w-11" src="~~/assets/images/card-ship.png" alt="Card chip"
          width="44" height="31">
        <div>
          <h5 class="font-heading text-muted-500 text-sm" x-text="cardholder">
            {{ props.cardnumber || '**** **** **** **** ' }}
          </h5>
        </div>
        <div>
          <p class="font-heading text-muted-400 text-xs"><span>{{ props.cardholder || 'Seu nome'
              }}</span></p>
        </div>
        <div class="font-heading text-muted-400 flex w-full items-center gap-2 text-xs">
          <div class="flex items-center gap-2"><span>EXP</span><span>

              {{ props.expire_month && props.expire_year ? `${props.expire_month}/${props.expire_year}` : '••/••' }}
            </span></div>
          <div class="flex items-center gap-2"><span>CVC</span><span>

              {{ props.cvv || ' •••' }}
            </span></div>
        </div>
      </div>
    </div>
    <div class="absolute end-5 top-4 flex">
      <UIcon :name="card.icon ?? 'mastercard'" size="3rem" />
    </div>
    <div class="absolute bottom-7 end-5 flex">
      <UIcon name="material-symbols:logo-dev" size="2rem" />
    </div>
  </div>
</template>