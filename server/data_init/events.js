export function eventsInit() {
    return [
        {
            "id" : "EVENTID_event:0",
            "title" : "Wild animal spotted !",
            "description" : "You spot a wild animal in the distance. It has not yet noticed you. Would that be a good opportunity for some hunting ?",
            "eventCode" : "0",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Let's grab our gear...",
                    "effectCode" : "i#ITEMID_item:1!3@ITEMID_item:4!-2",
                },
                {
                    "number" : "2",
                    "text" : "No we don't have time for this.",
                    "effectCode" : "",
                }
            ]
        }, 
        {
            "id" : "EVENTID_event:1",
            "name" : "Lucky findings on the road !",
            "description" : "You find some abandoned supplies alongside the road.",
            "effectCode" : "1",
            "options" : [
                {
                    "number" : "1",
                    "text" : "It's free real estate...",
                    "effect" : "i#ITEMID_item:1!1",
                }
            ]
        }
    ]
}