package nikita.app.vkapplication.data.repository

import nikita.app.vkapplication.data.model.ParseInfo

class RepositoryProducts {

//    fun productPageSource() = ProductPageSource(Common.apiService, 0)
    private lateinit var parseProductsList: ParseInfo
    private var skip: Int = 0

    fun setParseInfo(parseInfo: ParseInfo) {
        parseProductsList = parseInfo
    }

    fun getParseInfo() : ParseInfo {
        return parseProductsList
    }

    fun getSkip() : Int {
        skip += 10
        if (skip == 100) {
            skip = 0
        }
        return skip
    }

}