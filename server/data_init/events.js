export function eventsInit() {
    return [
        {
            "id" : "EVENTID_event:0",
            "name" : "Wild animal spotted !",
            "description" : "You spot a wild animal in the distance. It has not yet noticed you. Would that be a good opportunity for some hunting ?",
            "effectCode" : "null",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Let's grab our gear...",
                    "effect" : "i#ITEMID_item:1$3@ITEMID_item:4$-2",
                },
                {
                    "number" : "2",
                    "text" : "No we don't have time for this.",
                    "effect" : "",
                }
            ]
        }
    ]
}