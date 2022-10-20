package com.dracode.jsonui.model

import android.content.res.Resources
import org.json.JSONObject

internal class JSONViewConfigsMapper {
    companion object {
        fun mapFrom(json: String): JSONViewConfigs {
            val objectModel = JSONObject(json)

            val marginsObject = try {
                objectModel.getJSONObject("margin")
            }catch (e: Exception){JSONObject()}

            val paddingObject = try {
                objectModel.getJSONObject("padding")
            }catch (e: Exception){JSONObject()}

            val isEnabled = try {
                objectModel.getBoolean("isEnabled")
            }catch (e: Exception){true}

            var constraints = ConstraintsModel()

            try {
                val constraintsObject = objectModel.getJSONObject("constraints")
                constraints = getConstraints(constraintsObject)
            }catch (_: Exception){}

            val margins = getMargins(marginsObject)
            val paddings = getPaddings(paddingObject)

            return JSONViewConfigs(isEnabled, constraints, margins, paddings)
        }

        private fun getMargins(jsonObject: JSONObject): MarginsModel {
            val top: Int? = try {
                jsonObject.getInt("top").px
            }catch (e: Exception){null}

            val bottom: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            val left: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            val right: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            return MarginsModel(
                marginTop = top,
                marginBottom = bottom,
                marginLeft = left,
                marginRight = right,
            )
        }

        private fun getPaddings(jsonObject: JSONObject): PaddingsModel {
            val top: Int? = try {
                jsonObject.getInt("top").px
            }catch (e: Exception){null}

            val bottom: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            val left: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            val right: Int? = try {
                jsonObject.getInt("bottom").px
            }catch (e: Exception){null}

            return PaddingsModel(
                paddingTop = top,
                paddingBottom = bottom,
                paddingLeft = left,
                paddingRight = right,
            )
        }

        private fun getConstraints(jsonObject: JSONObject): ConstraintsModel {
            val bottomToTopOf = jsonObject.getConstraintFromKey("bottomToTopOf")
            val bottomToBottomOf = jsonObject.getConstraintFromKey("bottomToBottomOf")
            val topToTopOf = jsonObject.getConstraintFromKey("topToTopOf")
            val topToBottomOf = jsonObject.getConstraintFromKey("topToBottomOf")
            val endToEndOf = jsonObject.getConstraintFromKey("endToEndOf")
            val endToStartOf = jsonObject.getConstraintFromKey("endToStartOf")
            val startToStartOf = jsonObject.getConstraintFromKey("startToStartOf")
            val startToEndOf = jsonObject.getConstraintFromKey("startToEndOf")
            return ConstraintsModel(bottomToTopOf, bottomToBottomOf, topToTopOf, topToBottomOf, endToEndOf, endToStartOf, startToStartOf, startToEndOf)
        }

        private fun JSONObject.getConstraintFromKey(key: String): Constraints{
            return try{if (getString(key).isNullOrEmpty()) Constraints.Free else if (getString(key) == PARENT_CONSTRAINT) Constraints.Parent else if (getString(key) == "clear") Constraints.Clear else Constraints.Custom(getString(key))}
            catch (e:Exception){
                Constraints.Free
            }
        }
        private val Int.px: Int
            get() = (this * Resources.getSystem().displayMetrics.density).toInt()

        private const val PARENT_CONSTRAINT = "parent"
    }
}