uniform sampler2D tex;
uniform vec2 texelSize;
uniform float kernel[9];

void main() {
    vec2 offset = vec2(texelSize.x, 0);
    vec4 color = vec4(0);
    for (int i = 0; i < 9; i++) {
        color += texture2D(tex, gl_TexCoord[0].st + offset * (float(i) - 4.0)) * kernel[i];
    }
    gl_FragColor = color;
}