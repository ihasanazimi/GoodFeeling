package ir.ha.goodfeeling.data.models.local_entities.other

data class NewsItemEntity(
    val cover : Int,
    val title : String,
    val description : String ,
    val link : String,
    val seenCount : Int = 0,
    val timeAgo : String = "1 روز پیش"
)