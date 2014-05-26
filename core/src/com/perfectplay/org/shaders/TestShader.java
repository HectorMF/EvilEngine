package com.perfectplay.org.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class TestShader {
	/** Returns a new instance of the default shader used by SpriteBatch for GL2 when no shader is specified. */
	public static ShaderProgram createBlurShader(int width, int heigth) {
		final String FBO_W = Integer.toString(width);
		final String FBO_H = Integer.toString(heigth);
		
		String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "uniform mat4 u_projTrans;\n" //
			+ "varying vec4 v_color;\n" //
			+ "varying vec2 v_texCoords0;\n" //
			+ "varying vec2 v_texCoords1;\n" //
			+ "varying vec2 v_texCoords2;\n" //
			+ "varying vec2 v_texCoords3;\n" //
			+ "varying vec2 v_texCoords4;\n" //
			+ "#define FBO_W "
			+ FBO_W
			+ ".0\n"//
			+ "#define FBO_H "
			+ FBO_H
			+ ".0\n"//
			+ "const vec2 further = vec2(3.2307692308 / FBO_W, 3.2307692308 / FBO_H );\n" //
			+ "const vec2 closer = vec2(1.3846153846 / FBO_W, 1.3846153846 / FBO_H );\n" //
			+ "\n" //
			+ "void main()\n" //
			+ "{\n" //
			+ "   vec2 f = further;\n" //
			+ "   vec2 c = closer;\n" //
			+ "   v_texCoords0 = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0 - f;\n" //
			+ "   v_texCoords1 = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0 - c;\n" //
			+ "   v_texCoords2 = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "   v_texCoords3 = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0 + c;\n" //
			+ "   v_texCoords4 = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0 + f;\n" //
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "}\n";
		
		
		String fragmentShader = "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#define MED mediump\n"
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#define MED \n"
			+ "#endif\n" //
			+ "varying LOWP vec4 v_color;\n" //
			+ "varying MED vec2 v_texCoords0;\n" //
			+ "varying MED vec2 v_texCoords1;\n" //
			+ "varying MED vec2 v_texCoords2;\n" //
			+ "varying MED vec2 v_texCoords3;\n" //
			+ "varying MED vec2 v_texCoords4;\n" //
			+ "uniform sampler2D u_texture;\n" //
			+ "const float center = 0.2270270270;\n" //
			+ "const float close  = 0.3162162162;\n" //
			+ "const float far    = 0.0702702703;\n" //
			+ "void main()\n"//
			+ "{\n" //
			+ " gl_FragColor.rgb = far   * texture2D(u_texture, v_texCoords0).rgb\n" //
			+ "	      		+ close  * texture2D(u_texture, v_texCoords1).rgb\n" //
			+ "				+ center * texture2D(u_texture, v_texCoords2).rgb\n" //
			+ "				+ close  * texture2D(u_texture, v_texCoords3).rgb\n" //
			+ "				+ far    * texture2D(u_texture, v_texCoords4).rgb\n" //
			+ ";\n"//
			+ "}\n";

		ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
		if (shader.isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
		return shader;
	}
}
