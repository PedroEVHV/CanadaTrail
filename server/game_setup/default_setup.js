export function gameSetup() {
    return {
        "id" : "SETUP0",
        "name" : "Default start",
        "travelers" : 4,
        "eventCap" : 1,
        "food" : {
            "amount" : 10,
            "item" : "ITEMID_item:1",
            "mult" : 10
        },
        "water" : {
            "amount" : 10,
            "item" : "ITEMID_item:0",
            "mult" : 10
        },
        "medical" : {
            "amount" : 10,
            "item" : "ITEMID_item:10",
            "mult" : 10
        }
    }
}