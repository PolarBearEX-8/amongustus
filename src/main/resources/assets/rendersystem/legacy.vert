#version 120

varying vec4 vertColor;
varying vec2 fragCoord;

void main() {
    fragCoord = gl_Vertex.xy;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
    gl_TexCoord[0] = gl_MultiTexCoord0;

    vertColor = vec4(1.0, 1.0, 1.0, 1.0);
}
