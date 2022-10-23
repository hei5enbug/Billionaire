package makeme.billionaire.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import makeme.billionaire.model.BinanceResponseException


class FeignErrorDecoder : ErrorDecoder {

    private val objectMapper = jacksonObjectMapper().findAndRegisterModules()

    override fun decode(methodKey: String, response: Response): Exception? {
        val binanceResponseException =
            runCatching {
                objectMapper.readValue(response.body().asInputStream(), BinanceResponseException::class.java)
            }.getOrNull()
        binanceResponseException?.let {
            it.status = response.status()
            return it
        }
        return ErrorDecoder.Default().decode(methodKey, response)
    }

}