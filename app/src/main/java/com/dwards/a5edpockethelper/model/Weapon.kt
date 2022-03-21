package com.dwards.a5edpockethelper.model

import androidx.room.*
import com.dwards.a5edpockethelper.StringListConverter


@Entity(tableName = "Weapon")
@TypeConverters(StringListConverter::class)
data class Weapon(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var charOwnerID: Int = 0,
    var name: String = "Magnolia",
    var reach: Int = 0,
    var damageType: String = "Slashing",
    var damageTypePosition: Int = 0,
    var attackStat: String = "",
    var rangeType: Int = 0,
    var handedType: Int = 0,

    var statAttackBonus: Int = 3,
    var magicAttackBonus: Int = 0,
    var miscAttackBonus: Int = 0,
    var profAttackBnus: Int = 0,

    var weaponProf: Boolean = false,
    var description: String = "Magic Longsword +1, 1d8+1 damage. The sword holds up to 6 charges and restores 1d4+1 damage at dawn. If a creature wielding magic is killed by this blade, Magnolia absorbs some of its power and restores 2 charges.\nYou may expend sword charges to cast the following spells:\n-While spending 3 charges, you can react to someone else's spell with a Counter Spell.\n-Spending 2 charges will allow you to perform a Wound Healing spell.\n-When you spend 1 load, you can cast the Armor Mage spell.\n If you manage to undo an enemy spell with this skill, you capture an element of the spell, then the empty space on your sword blade is filled with the power of the spell, and will deal an additional 1d8 damage of the appropriate type for 10 minutes, if during this time period you undo the spell and want to absorb its energy, the properties from the previous absorption do not work. When you use a spell or incantation, the sword is fueled by its magical energy and the next attack will deal an additional 1d8 damage with the same element.",

    var damageStatMod: String = "",
    var statApplyToDmg: Boolean = false,
    var statDamageBonus: Int = 5,
    var magicDamageBonus: Int = 3,
    var miscDamageBonus: Int = 0,

    var hitDice1Count: Int = 0,
    var hitDice1Size: Int = 0,
    var hitDice2Have: Boolean = false,
    var hitDice2Count: Int = 0,
    var hitDice2Size: Int = 0,
    var hitDice3Have: Boolean = false,
    var hitDice3Count: Int = 0,
    var hitDice3Size: Int = 0,
)


