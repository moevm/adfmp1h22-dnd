package com.dwards.a5edpockethelper.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dwards.a5edpockethelper.StringListConverter


@Entity(tableName = "Weapon")
@TypeConverters(StringListConverter::class)
data class Weapon(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var charOwnerID: Int = 0,
    var name: String = "Weapon",

    var range: String = "0",
    var rangeType: Int = 0,

    var damageTypePosition: Int = 0,
    var handedType: Int = 0,

    var weaponProf: Boolean = false,
    var attackStatType: Int = 0,
    var statAttackBonus: Int = 0,
    var magicAttackBonus: Int = 0,
    var miscAttackBonus: Int = 0,
    var profAttackBnus: Int = 0,


    var statApplyToDmg: Boolean = false,
    var statDamageBonus: Int = 0,
    var magicDamageBonus: Int = 0,
    var miscDamageBonus: Int = 0,

    var damageDice1Count: Int = 0,
    var damageDice1Size: Int = 0,
    var damageDice2Have: Boolean = false,
    var damageDice2Count: Int = 0,
    var damageDice2Size: Int = 0,
    var damageDice3Have: Boolean = false,
    var damageDice3Count: Int = 0,
    var damageDice3Size: Int = 0,

    var description: String = "Magic Longsword +1, 1d8+1 damage. The sword holds up to 6 charges and restores 1d4+1 damage at dawn. If a creature wielding magic is killed by this blade, Magnolia absorbs some of its power and restores 2 charges.\nYou may expend sword charges to cast the following spells:\n-While spending 3 charges, you can react to someone else's spell with a Counter Spell.\n-Spending 2 charges will allow you to perform a Wound Healing spell.\n-When you spend 1 load, you can cast the Armor Mage spell.\n If you manage to undo an enemy spell with this skill, you capture an element of the spell, then the empty space on your sword blade is filled with the power of the spell, and will deal an additional 1d8 damage of the appropriate type for 10 minutes, if during this time period you undo the spell and want to absorb its energy, the properties from the previous absorption do not work. When you use a spell or incantation, the sword is fueled by its magical energy and the next attack will deal an additional 1d8 damage with the same element.",
)


