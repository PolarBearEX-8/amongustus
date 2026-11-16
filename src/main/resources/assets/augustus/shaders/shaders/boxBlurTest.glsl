#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;
uniform float radius;

varying vec2 uv;

void main() {
    vec4 color = vec4(0.0);
    vec2 texcoord = uv;
    float total = 0.0;

    for (float i = -radius; i <= radius; i++) {
        float w = 1.0;
        color += w * texture2D(texture, texcoord + i * texelSize * direction);
        total += w;
    }

    gl_FragColor = color / total;
}
