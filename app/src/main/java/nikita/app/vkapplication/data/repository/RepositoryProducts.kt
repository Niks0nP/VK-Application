package nikita.app.vkapplication.data.repository

import nikita.app.vkapplication.data.model.ParseInfo

class RepositoryProducts {

    private lateinit var parseProductsList: ParseInfo

    fun setParseInfo(parseInfo: ParseInfo) {
        parseProductsList = parseInfo
    }
    
    fun getParseInfo() : ParseInfo {
        return parseProductsList
    }
}