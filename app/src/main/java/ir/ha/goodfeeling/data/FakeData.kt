package ir.ha.goodfeeling.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import ir.ha.goodfeeling.R
import ir.ha.goodfeeling.data.models.enums.CurrencyType
import ir.ha.goodfeeling.data.models.local_entities.other.CityEntity
import ir.ha.goodfeeling.data.models.local_entities.other.CurrencyPriceEntity
import ir.ha.goodfeeling.data.models.local_entities.other.NewsItemEntity
import ir.ha.goodfeeling.data.models.local_entities.other.OccasionsOfTheDayEntity
import ir.ha.goodfeeling.ui.theme.GreenColor
import ir.ha.goodfeeling.ui.theme.RedColor


val fakeOccasionsOfTheDayList: ArrayList<OccasionsOfTheDayEntity> =
    arrayListOf<OccasionsOfTheDayEntity>(
        OccasionsOfTheDayEntity("روز بزرگداشت فردوسی", isHoliday = true),
        OccasionsOfTheDayEntity("روز جوان", isHoliday = false),
        OccasionsOfTheDayEntity("روز مهندس", isHoliday = false),
    )


val fakeBitPriceList: ArrayList<CurrencyPriceEntity> = arrayListOf<CurrencyPriceEntity>(
    CurrencyPriceEntity(
        currencyName = "بیت کوین",
        currencyFlagId = R.drawable.bitcoin,
        currencyPrice = "83250",
        currencyChangePercent = "3.1",
        currencyChangePercentColor = GreenColor,
        currencyUnitType = CurrencyType.Dollar
    ),
    CurrencyPriceEntity(
        currencyName = "اتریوم",
        currencyFlagId = R.drawable.ethereum,
        currencyPrice = "1750",
        currencyChangePercent = "1.8",
        currencyChangePercentColor = RedColor,
        currencyUnitType = CurrencyType.Dollar
    ),
    CurrencyPriceEntity(
        currencyName = "تتر",
        currencyFlagId = R.drawable.tether,
        currencyPrice = "99850",
        currencyChangePercent = "2.5",
        currencyChangePercentColor = RedColor,
        currencyUnitType = CurrencyType.Toman
    )
)


val fakeGoldPriceList: ArrayList<CurrencyPriceEntity> = arrayListOf<CurrencyPriceEntity>(
    CurrencyPriceEntity(
        currencyName = "طلا 18 عیار",
        currencyFlagId = R.drawable.gold,
        currencyPrice = "7775000",
        currencyChangePercent = "5.1",
        currencyChangePercentColor = GreenColor,
        currencyUnitType = CurrencyType.Toman
    ),
    CurrencyPriceEntity(
        currencyName = "سکه امامی",
        currencyFlagId = R.drawable.golden_coin,
        currencyPrice = "98103250",
        currencyChangePercent = "1.8",
        currencyChangePercentColor = RedColor,
        currencyUnitType = CurrencyType.Toman
    )
)

val fakeCurrencyPriceList: ArrayList<CurrencyPriceEntity> = arrayListOf<CurrencyPriceEntity>(
    CurrencyPriceEntity(
        currencyName = "دلار",
        currencyFlagId = R.drawable.us,
        currencyPrice = "99250",
        currencyChangePercent = "3.1",
        currencyChangePercentColor = RedColor,
        currencyUnitType = CurrencyType.Toman
    ),
    CurrencyPriceEntity(
        currencyName = "یورو",
        currencyFlagId = R.drawable.euro,
        currencyPrice = "106250",
        currencyChangePercent = "1.8",
        currencyChangePercentColor = GreenColor,
        currencyUnitType = CurrencyType.Toman
    ),
    CurrencyPriceEntity(
        currencyName = "پوند",
        currencyFlagId = R.drawable.england,
        currencyPrice = "108150",
        currencyChangePercent = "2.5",
        currencyChangePercentColor = RedColor,
        currencyUnitType = CurrencyType.Toman
    )
)


fun getFakeCitiesList(): SnapshotStateList<CityEntity> {
    val mutableStateListOf = mutableStateListOf<CityEntity>()
    val _cities = arrayListOf<CityEntity>(
        CityEntity(cityName = "تهران", location = "", selected = false),
        CityEntity(cityName = "کاشان", location = "", selected = false),
        CityEntity(cityName = "اصفهان", location = "", selected = false),
        CityEntity(cityName = "اهواز", location = "", selected = false),
        CityEntity(cityName = "مازندران", location = "", selected = false),
        CityEntity(cityName = "زنجان", location = "", selected = false),
        CityEntity(cityName = "تبریز", location = "", selected = false),
        CityEntity(cityName = "گلستان", location = "", selected = false),
        CityEntity(cityName = "بروجرد", location = "", selected = false),
        CityEntity(cityName = "یاسوج", location = "", selected = false),
        CityEntity(cityName = "لرستان", location = "", selected = false),
        CityEntity(cityName = "مشهد", location = "", selected = false),
        CityEntity(cityName = "اردبیل", location = "", selected = false),
    )
    val temp = _cities.sortedBy { it.cityName }
    temp.forEach {
        mutableStateListOf.add(it)
    }
    return mutableStateListOf
}


fun getFakeNews() = arrayListOf<NewsItemEntity>(
    NewsItemEntity(
        cover = R.drawable.tara,
        title = "افزایش ۴۰ درصدی قیمت خودرو در ایران",
        description = "رئیس اتحادیه نمایشگاه‌داران و فروشندگان خودروی تهران اعلام کرد که قیمت خودرو در ایران طی یک ماه گذشته ۴۰ درصد افزایش یافته است. این افزایش عمدتاً به دلیل محدودیت در عرضه خودرو و افزایش نرخ ارز بوده است.",
        link = "https://www.trt.net.tr/persian/yrn/2023/01/09/qymt-khwdrw-dr-yrn-40-drsd-fzysh-yft-1929991",
        seenCount = 4,
        timeAgo = "2 روز پیش"
    ),
    NewsItemEntity(
        cover = R.drawable.hormoz,
        title = "خاک هرمزگان به غارت رفت",
        description = "گزارش‌ها حاکی از آن است که در استان هرمزگان، برداشت غیرمجاز خاک ساحلی توسط برخی شرکت‌ها و افراد سودجو صورت گرفته است. این اقدام باعث نگرانی‌های زیست‌محیطی و اعتراض فعالان محیط زیست شده است.",
        link = "https://www.isna.ir/news/1401120503003",
        seenCount = 896,
        timeAgo = "2 روز پیش"
    ),
    NewsItemEntity(
        cover = R.drawable.dolat,
        title = "آخرین اخبار مذاکرات مستقیم",
        description = "در جریان مذاکرات اخیر، مقامات ایرانی و نمایندگان کشورهای غربی به بررسی راه‌های احیای توافق هسته‌ای پرداختند. این مذاکرات با هدف کاهش تنش‌ها و بازگشت به تعهدات قبلی انجام شده است.",
        link = "https://www.irna.ir/news/85012345",
        seenCount = 1830,
        timeAgo = "2 روز پیش"
    )
)