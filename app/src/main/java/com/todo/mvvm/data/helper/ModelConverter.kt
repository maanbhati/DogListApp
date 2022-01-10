package com.todo.mvvm.data.helper

import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.mvvm.data.remote.DogDetailsDomainModel
import com.todo.mvvm.data.remote.DogListDomainModel
import com.todo.mvvm.utils.HYPHEN
import com.todo.networkmodule.dto.Message
import javax.inject.Inject

class ModelConverter @Inject constructor() {

    fun convertFromDtoToEntityModel(
        response: Message,
    ): DogListEntity {
        val list = mutableListOf<String>()
        list.addAll(getBreedList(response.affenpinscher, response))
        list.addAll(getBreedList(response.african, response))
        list.addAll(getBreedList(response.airedale, response))
        list.addAll(getBreedList(response.akita, response))
        list.addAll(getBreedList(response.appenzeller, response))
        list.addAll(getBreedList(response.australian, response))
        list.addAll(getBreedList(response.basenji, response))
        list.addAll(getBreedList(response.beagle, response))
        list.addAll(getBreedList(response.bluetick, response))
        list.addAll(getBreedList(response.borzoi, response))
        list.addAll(getBreedList(response.bouvier, response))
        list.addAll(getBreedList(response.boxer, response))
        list.addAll(getBreedList(response.brabancon, response))
        list.addAll(getBreedList(response.briard, response))
        list.addAll(getBreedList(response.buhund, response))
        list.addAll(getBreedList(response.bulldog, response))
        list.addAll(getBreedList(response.bullterrier, response))
        list.addAll(getBreedList(response.cattledog, response))
        list.addAll(getBreedList(response.chihuahua, response))
        list.addAll(getBreedList(response.chow, response))
        list.addAll(getBreedList(response.clumber, response))
        list.addAll(getBreedList(response.cockapoo, response))
        list.addAll(getBreedList(response.collie, response))
        list.addAll(getBreedList(response.coonhound, response))
        list.addAll(getBreedList(response.corgi, response))
        list.addAll(getBreedList(response.cotondetulear, response))
        list.addAll(getBreedList(response.dachshund, response))
        list.addAll(getBreedList(response.dalmatian, response))
        list.addAll(getBreedList(response.dane, response))
        list.addAll(getBreedList(response.deerhound, response))
        list.addAll(getBreedList(response.dhole, response))
        list.addAll(getBreedList(response.dingo, response))
        list.addAll(getBreedList(response.doberman, response))
        list.addAll(getBreedList(response.elkhound, response))
        list.addAll(getBreedList(response.entlebucher, response))
        list.addAll(getBreedList(response.eskimo, response))
        list.addAll(getBreedList(response.finnish, response))
        list.addAll(getBreedList(response.frise, response))
        list.addAll(getBreedList(response.germanshepherd, response))
        list.addAll(getBreedList(response.greyhound, response))
        list.addAll(getBreedList(response.groenendael, response))
        list.addAll(getBreedList(response.havanese, response))
        list.addAll(getBreedList(response.hound, response))
        list.addAll(getBreedList(response.husky, response))
        list.addAll(getBreedList(response.keeshond, response))
        list.addAll(getBreedList(response.kelpie, response))
        list.addAll(getBreedList(response.komondor, response))
        list.addAll(getBreedList(response.kuvasz, response))
        list.addAll(getBreedList(response.labradoodle, response))
        list.addAll(getBreedList(response.labrador, response))
        list.addAll(getBreedList(response.leonberg, response))
        list.addAll(getBreedList(response.lhasa, response))
        list.addAll(getBreedList(response.malamute, response))
        list.addAll(getBreedList(response.malinois, response))
        list.addAll(getBreedList(response.maltese, response))
        list.addAll(getBreedList(response.mastiff, response))
        list.addAll(getBreedList(response.mexicanhairless, response))
        list.addAll(getBreedList(response.mix, response))
        list.addAll(getBreedList(response.mountain, response))
        list.addAll(getBreedList(response.newfoundland, response))
        list.addAll(getBreedList(response.otterhound, response))
        list.addAll(getBreedList(response.ovcharka, response))
        list.addAll(getBreedList(response.papillon, response))
        list.addAll(getBreedList(response.pekinese, response))
        list.addAll(getBreedList(response.pembroke, response))
        list.addAll(getBreedList(response.pinscher, response))
        list.addAll(getBreedList(response.pitbull, response))
        list.addAll(getBreedList(response.pointer, response))
        list.addAll(getBreedList(response.pomeranian, response))
        list.addAll(getBreedList(response.poodle, response))
        list.addAll(getBreedList(response.pug, response))
        list.addAll(getBreedList(response.puggle, response))
        list.addAll(getBreedList(response.pyrenees, response))
        list.addAll(getBreedList(response.redbone, response))
        list.addAll(getBreedList(response.retriever, response))
        list.addAll(getBreedList(response.ridgeback, response))
        list.addAll(getBreedList(response.rottweiler, response))
        list.addAll(getBreedList(response.saluki, response))
        list.addAll(getBreedList(response.samoyed, response))
        list.addAll(getBreedList(response.schipperke, response))
        list.addAll(getBreedList(response.schnauzer, response))
        list.addAll(getBreedList(response.setter, response))
        list.addAll(getBreedList(response.sheepdog, response))
        list.addAll(getBreedList(response.shiba, response))
        list.addAll(getBreedList(response.shihtzu, response))
        list.addAll(getBreedList(response.spaniel, response))
        list.addAll(getBreedList(response.springer, response))
        list.addAll(getBreedList(response.stbernard, response))
        list.addAll(getBreedList(response.terrier, response))
        list.addAll(getBreedList(response.tervuren, response))
        list.addAll(getBreedList(response.vizsla, response))
        list.addAll(getBreedList(response.waterdog, response))
        list.addAll(getBreedList(response.weimaraner, response))
        list.addAll(getBreedList(response.whippet, response))
        list.addAll(getBreedList(response.wolfhound, response))

        return DogListEntity(list.distinct().sortedBy { it })
    }

    private fun getBreedList(list: List<String>, response: Message): List<String> {
        val listData = mutableListOf<String>()
        val breedName = getBreedName(list, response)

        if (list.isNotEmpty()) {
            for (i in list) {
                listData.add("$i$HYPHEN$breedName")
            }
        } else {
            listData.add(breedName)
        }
        return listData
    }

    private fun getBreedName(breed: List<String>, response: Message): String {
        return when {
            breed === response.affenpinscher -> "affenpinscher"
            breed === response.african -> "african"
            breed === response.airedale -> "airedale"
            breed === response.akita -> "akita"
            breed === response.appenzeller -> "appenzeller"
            breed === response.australian -> "australian"
            breed === response.basenji -> "basenji"
            breed === response.beagle -> "beagle"
            breed === response.bluetick -> "bluetick"
            breed === response.borzoi -> "borzoi"
            breed === response.bouvier -> "bouvier"
            breed === response.boxer -> "boxer"
            breed === response.brabancon -> "brabancon"
            breed === response.briard -> "briard"
            breed === response.buhund -> "buhund"
            breed === response.bulldog -> "bulldog"
            breed === response.bullterrier -> "bullterrier"
            breed === response.cattledog -> "cattledog"
            breed === response.chihuahua -> "chihuahua"
            breed === response.chow -> "chow"
            breed === response.clumber -> "clumber"
            breed === response.cockapoo -> "cockapoo"
            breed === response.collie -> "collie"
            breed === response.coonhound -> "coonhound"
            breed === response.corgi -> "corgi"
            breed === response.cotondetulear -> "cotondetulear"
            breed === response.dachshund -> "dachshund"
            breed === response.dalmatian -> "dalmatian"
            breed === response.dane -> "dane"
            breed === response.deerhound -> "deerhound"
            breed === response.dhole -> "dhole"
            breed === response.dingo -> "dingo"
            breed === response.doberman -> "doberman"
            breed === response.elkhound -> "elkhound"
            breed === response.entlebucher -> "entlebucher"
            breed === response.eskimo -> "eskimo"
            breed === response.finnish -> "finnish"
            breed === response.frise -> "frise"
            breed === response.germanshepherd -> "germanshepherd"
            breed === response.greyhound -> "greyhound"
            breed === response.groenendael -> "groenendael"
            breed === response.havanese -> "havanese"
            breed === response.hound -> "hound"
            breed === response.husky -> "husky"
            breed === response.keeshond -> "keeshond"
            breed === response.kelpie -> "kelpie"
            breed === response.komondor -> "komondor"
            breed === response.kuvasz -> "kuvasz"
            breed === response.labradoodle -> "labradoodle"
            breed === response.labrador -> "labrador"
            breed === response.leonberg -> "leonberg"
            breed === response.lhasa -> "lhasa"
            breed === response.malamute -> "malamute"
            breed === response.malinois -> "malinois"
            breed === response.maltese -> "maltese"
            breed === response.mastiff -> "mastiff"
            breed === response.mexicanhairless -> "mexicanhairless"
            breed === response.mix -> "mix"
            breed === response.mountain -> "mountain"
            breed === response.newfoundland -> "newfoundland"
            breed === response.otterhound -> "otterhound"
            breed === response.ovcharka -> "ovcharka"
            breed === response.papillon -> "papillon"
            breed === response.pekinese -> "pekinese"
            breed === response.pembroke -> "pembroke"
            breed === response.pinscher -> "pinscher"
            breed === response.pitbull -> "pitbull"
            breed === response.pointer -> "pointer"
            breed === response.pomeranian -> "pomeranian"
            breed === response.poodle -> "poodle"
            breed === response.pug -> "pug"
            breed === response.puggle -> "puggle"
            breed === response.pyrenees -> "pyrenees"
            breed === response.redbone -> "redbone"
            breed === response.retriever -> "retriever"
            breed === response.ridgeback -> "ridgeback"
            breed === response.rottweiler -> "rottweiler"
            breed === response.saluki -> "saluki"
            breed === response.samoyed -> "samoyed"
            breed === response.schipperke -> "schipperke"
            breed === response.schnauzer -> "schnauzer"
            breed === response.setter -> "setter"
            breed === response.sheepdog -> "sheepdog"
            breed === response.shiba -> "shiba"
            breed === response.shihtzu -> "shihtzu"
            breed === response.spaniel -> "spaniel"
            breed === response.springer -> "springer"
            breed === response.stbernard -> "stbernard"
            breed === response.terrier -> "terrier"
            breed === response.tervuren -> "tervuren"
            breed === response.vizsla -> "vizsla"
            breed === response.waterdog -> "waterdog"
            breed === response.weimaraner -> "weimaraner"
            breed === response.whippet -> "whippet"
            breed === response.wolfhound -> "wolfhound"
            else -> "Unknown"
        }
    }

    fun convertEntityToDomainModel(dogListEntity: DogListEntity?): DogListDomainModel {
        return if (dogListEntity != null)
            DogListDomainModel(dogListEntity.dogList)
        else
            DogListDomainModel(listOf())
    }

    fun convertFromDtoToDogDetailsEntity(dogImageUrl: String, dogName: String): DogDetailsEntity {
        return DogDetailsEntity(dogImageUrl, dogName)
    }

    fun convertDogDetailsEntityToDomainModel(dogDetailsEntity: DogDetailsEntity?): DogDetailsDomainModel {
        return if (dogDetailsEntity != null)
            DogDetailsDomainModel(dogDetailsEntity.dogImageUrl, dogDetailsEntity.dogName)
        else
            DogDetailsDomainModel("", "No data available.")
    }
}