@import gui4me.utils.Message

@param Message message

@if(message != null && message.isError())
    <div class="message error">${message.getMessage()}
        @if(message.getLink() != null)
        <a href="${message.getLinkUrl()}">${message.getLinkText()}</a>
        @endif
    </div>
@endif

