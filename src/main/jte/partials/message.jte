@import gui4me.utils.Message

@param Message message

@if(message != null)
    <div class="message-container show" id="message-container-${message.getId()}">
        <button class="close-btn" onclick="dismissMessage('${message.getId()}')"><i class="fa fa-close"></i></button>
        <div class="message ${message.isError() ? "error" : "success"}">
            ${message.getMessage()}
            @if(message.getLink() != null)
                <a href="${message.getLinkUrl()}">${message.getLinkText()}</a>
            @endif
        </div>
    </div>
@endif
