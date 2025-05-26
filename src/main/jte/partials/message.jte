@import gui4me.utils.Message

@param Message message

@if(message != null)
    <div class="@if(message.isError()) message error @else message success @endif">
        ${message.getMessage()}
        @if(message.getLink() != null)
            <a href="${message.getLinkUrl()}">${message.getLinkText()}</a>
        @endif
    </div>
@endif

