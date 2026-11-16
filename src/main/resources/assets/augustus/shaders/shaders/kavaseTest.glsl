#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform float radius;
uniform float weight[100];

varying vec2 uv;

void main() {
    vec4 color = vec4(0.0);
    vec2 texcoord = uv;
    float total = 0.0;

    for (float i = 0.0; i <= radius; i++) {
        color += weight[int(i)] * texture2D(texture, texcoord + i * texelSize);
        color += weight[int(i)] * texture2D(texture, texcoord - i * texelSize);
        color += weight[int(i)] * texture2D(texture, texcoord + vec2(i * texelSize.x, -i * texelSize.y));
        color += weight[int(i)] * texture2D(texture, texcoord - vec2(i * texelSize.x, -i * texelSize.y));
        total += 4.0 * weight[int(i)];
    }
    gl_FragColor = color / total;
}

